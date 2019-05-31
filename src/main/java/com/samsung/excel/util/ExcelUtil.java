package com.samsung.excel.util;

import com.samsung.excel.pivot.PivotConfig;
import com.samsung.excel.pivot.filter.Filter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtil {

    private static final List<String> headersWanted = ExcelHeaderEnum.getAllHeadersName();


    public static void copySheet(XSSFSheet source, XSSFSheet destination) {

        int destinationRowIndex = 0;

        Iterator<Row> iterator = source.iterator();

        while (iterator.hasNext()) {
            Row sourceRow;

            try {
                sourceRow = iterator.next();

                XSSFRow destinationRow = destination.createRow(destinationRowIndex++);

                int destinationColumnIndex = 0;

                for (Cell sourceCell : sourceRow) {

                    XSSFCell destinationCell = destinationRow.createCell(destinationColumnIndex++);
                    sourceCell.setCellType(CellType.STRING);

                    destinationCell.setCellValue(sourceCell.getStringCellValue());
                }

            } catch (Exception e) {
                System.out.println("Removed row , continue with the others ");
            }
        }
    }


//    This method get the max column index in the accepted excel headers


    public static int getMaxColumnIndexOfHeaders(Row sheet, List<Cell> excelHeaders) {

        int maxIndex = 0;

        for (Cell excelCell : excelHeaders) {

            excelCell.setCellType(CellType.STRING);

            for (Cell sheetCell : sheet) {

                sheetCell.setCellType(CellType.STRING);

                if (sheetCell.getStringCellValue().equals(excelCell.getStringCellValue())) {
                    if (maxIndex < sheetCell.getColumnIndex()) {
                        maxIndex = sheetCell.getColumnIndex();
                    }
                    break;
                }
            }
        }

        return maxIndex;
    }

    public static ExcelConstructUtil getExcelConstructUtil(Row header) {

        List<Cell> actualHeaders = new ArrayList<>();
        int maxIndex=0;


        for (Cell cell : header) {

            if (headersWanted.contains(cell.getStringCellValue())) {

                if(maxIndex < cell.getColumnIndex()){
                    maxIndex = cell.getColumnIndex();
                }

                actualHeaders.add(cell);
            }
        }

        return new ExcelConstructUtil(actualHeaders, maxIndex);
    }

    public static Optional<Cell> getRequiredHeaderByName(List<Cell> header, String headerName) {

        for (Cell headerCell : header) {

            if (headerCell.getStringCellValue().equals(headerName)) {
                return Optional.of(headerCell);
            }
        }

        return Optional.empty();
    }

    public static Map<Integer, List<String>> getFilterMap(List<Cell> headers, PivotConfig pivotConfig) {

        Map<Integer, List<String>> filterMap = new HashMap<>();

        Filter filter = pivotConfig.getFilter();

        for (Map.Entry<ExcelHeaderEnum, List<String>> entries : filter.getFilterMap().entrySet()) {

            for (Cell cell : headers) {
                if (cell.getStringCellValue().trim().equals(entries.getKey().getHeaderName())) {
                    int columnIndex = cell.getColumnIndex();
                    filterMap.put(columnIndex, entries.getValue());
                    break;
                }

            }
        }

        return filterMap;
    }

    public List<Row> getRowsForErase(Sheet sheet, Map<Integer, List<String>> filterMap) {

        List<Row> rowIdsForRemove = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.rowIterator();

//        skip past header
        rowIterator.next();

        for (; rowIterator.hasNext(); ) {
            Row row = rowIterator.next();


//            TODO de urmarit
            finished:
            for (Cell cell : row) {
                for (Map.Entry<Integer, List<String>> entry : filterMap.entrySet()) {
                    if (cell.getColumnIndex() == entry.getKey() && entry.getValue().contains(cell.getStringCellValue().trim())) {
                        rowIdsForRemove.add(row);
                        break finished;
                    }
                }
            }
        }

        return rowIdsForRemove;

    }

    public void eraseRows(Sheet sheet, Map<Integer, List<String>> filterMap) {

        getRowsForErase(sheet, filterMap).forEach(sheet::removeRow);

    }


//        This return a map that has the following form
//        The key of the map is the column that contain the value that need to be filtered


//               columnNumber          Values to be filtered
//         Map :  1             ->        {'rh','it'}
//

    public void writeSheetToSpecificFile(Workbook workbook, String newfilename) {

        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream(newfilename + new Random().nextInt() + ".xlsx", false)) {
                workbook.write(fileOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}