package com.samsung.excel.parser;

import com.cookingfox.guava_preconditions.Preconditions;
import com.samsung.excel.util.ExcelException;
import com.samsung.excel.util.ExcelHeaderEnum;
import com.samsung.excel.util.ExcelUtil;
import lombok.Data;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

@Data
public class Excel {

    private static final Map<String, List<Row>> excel_by_service_name = new HashMap<>();
    private static final String PIVOT1 = "Pivot1";
    private static XSSFRow row;
    private static ArrayList<Cell> actualHeaders;
    private static Map<Row, List<Cell>> excel = new HashMap<>();
    private static Map<String, ArrayList<Row>> SO_BY_PARTNER = new HashMap<>();
    private List<Cell> actualHeaders1;

    public Excel() {
    }

    public void parseFile(File file) throws IOException {

        Preconditions.checkNotNull(file, "File cannot be null while parsing");
        XSSFWorkbook workbook;

        try (InputStream fileInput = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fileInput);
        }

        createSheetForPivots(workbook);


        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFSheet pivotSheet = workbook.getSheetAt(1);


        Iterator<Row> rowIterator = sheet.iterator();
        Row header = rowIterator.next();
        actualHeaders1 = ExcelUtil.getRequiredHeaders(header);

        System.out.println(" Actual actualHeaders");

        actualHeaders1.forEach(System.out::println);

        for (Cell cell : actualHeaders1) {
            System.out.println(cell.getStringCellValue());

        }


        createPivot(sheet, pivotSheet);

        try (FileOutputStream fileOutputStream = new FileOutputStream("TestExample.xlsx", false)) {
            workbook.write(fileOutputStream);
        }

//        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/ExcelPivot.xlsx", false)) {
//            workbook.write(fileOutputStream);
//        }
//
//        Cell requiredExcelHeader = getRequiredExcelHeader(actualHeaders1, ExcelHeaderEnum.SERVICE_TYPE.getHeaderName());
//
//
////       build a map that keys are SERVICE TYPE
//        while (rowIterator.hasNext()) {
//
//            Row row = rowIterator.next();
//
//            for (Cell cellFromRow : row) {
//
//                if (cellFromRow.getColumnIndex() == requiredExcelHeader.getColumnIndex()) {
//                    populateExcelMap(cellFromRow.getStringCellValue(), row);
//                }
//            }
//        }

        System.out.println("Excel Parsed");

//        while (rowIterator.hasNext()) {
//
//
//            row = (XSSFRow) rowIterator.next();
//
//            Iterator<Cell> cellIterator = row.cellIterator();
//
//
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//
//
//                switch (cell.getCellType()) {
//                    case NUMERIC:
//                        System.out.print(cell.getNumericCellValue() + " \t\t\t\t ");
//                        break;
//
//                    case STRING:
//                        System.out.print(
//                                cell.getStringCellValue() + " \t\t\t\t\t\t\t\t ");
//
//                        break;
//
////                    case _NONE:
////                        cell.setCellValue("N/A");
////                        break;
////
////                    case ERROR:
////                        cell.setCellValue("N/A");
////                        break;
//
//                    case BLANK:
//                        for (int i = cell.getColumnIndex(); i <= row.getLastCellNum(); i++) {
//                            if (cell.getCellType() == CellType.BLANK) {
//                                cell.setCellValue("N/A");
//                            }
//
//                        }
//
////                       row.createCell(cell.getColumnIndex(), CellType.STRING);
////                        cell.setCellType(CellType.STRING);
////                        String cellValue = "N/A";
//////                        cell.setCellValue(cellValue);
////                        System.out.println(cell.getColumnIndex());
////
//                        break;
//
//                }
//            }
//            System.out.println();
//        }
    }

    private void createSheetForPivots(XSSFWorkbook workbook) {
        workbook.createSheet(PIVOT1);
    }

    private void createPivot(XSSFSheet sheet, XSSFSheet pivotSheet) {

        CellReference position = new CellReference(0, 0);

//        AreaReference areaReference = new AreaReference(position,
//                new CellReference(sheet.getLastRowNum(), actualHeaders1.size()), SpreadsheetVersion.EXCEL2007);

        AreaReference areaReference = new AreaReference(position,
                new CellReference(sheet.getLastRowNum(), actualHeaders1.size()), SpreadsheetVersion.EXCEL2007);


        XSSFPivotTable pivotTable = pivotSheet.createPivotTable(areaReference, position, sheet);
//        XSSFPivotTable pivotTable = sheet.createPivotTable(areaReference, new CellReference(sheet.getLastRowNum() + 10, 1));

        pivotTable.addRowLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.SERVICE_TYPE));
//        pivotTable.addRowLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.SERVICE_TYPE_TXT));
//        pivotTable.addRowLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.REASON_TEXT));

        pivotTable.addColLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.PENDING_DAYS));


        pivotTable.addColumnLabel(DataConsolidateFunction.COUNT, getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.Service_order));

//        pivotTable.addReportFilter(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.SERVICE_TYPE_TXT));

    }

    private int getColumnIndexOf(List<Cell> actualHeaders1, ExcelHeaderEnum serviceType) {
        Optional<Cell> first = actualHeaders1.stream()
                .filter(cell -> cell.getStringCellValue().equals(serviceType.getHeaderName()))
                .findFirst();


        return first.map(Cell::getColumnIndex).orElse(-1);
    }


    private void populateExcelMap(String stringCellValue, Row row) {

        if (excel_by_service_name.containsKey(stringCellValue)) {
            excel_by_service_name.get(stringCellValue).add(row);
        } else {

            List<Row> rowsByServiceName = new ArrayList<>();
            rowsByServiceName.add(row);
            excel_by_service_name.put(stringCellValue, rowsByServiceName);
        }
    }


    public Cell getRequiredExcelHeader(List<Cell> headers, String headerValue) {
        Optional<Cell> requiredHeaderByName = ExcelUtil.getRequiredHeaderByName(headers, headerValue);

        if (requiredHeaderByName.isPresent()) {
            return requiredHeaderByName.get();
        }

        throw new ExcelException("Value " + headerValue + " not found in the header cannot proceed with parsing");
    }


}