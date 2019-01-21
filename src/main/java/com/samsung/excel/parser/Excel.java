package com.samsung.excel.parser;

import com.cookingfox.guava_preconditions.Preconditions;
import com.samsung.excel.util.ExcelException;
import com.samsung.excel.util.ExcelHeaderEnum;
import com.samsung.excel.util.ExcelUtil;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Data
public class Excel {

    private static final Map<String, List<Row>> excel_by_service_name = new HashMap<>();
    private static XSSFRow row;
    private static ArrayList<Cell> actualHeaders;
    private static Map<Row, List<Cell>> excel = new HashMap<>();
    private static Map<String, ArrayList<Row>> SO_BY_PARTNER = new HashMap<>();

    public Excel() {
    }

    public void parseFile(File file) throws IOException {

        Preconditions.checkNotNull(file, "File cannot be null while parsing");
        XSSFWorkbook workbook;

        try (InputStream fileInput = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fileInput);
        }


        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Row header = rowIterator.next();
        List<Cell> actualHeaders = ExcelUtil.getRequiredHeaders(header);

        System.out.println(" Actual actualHeaders");

        actualHeaders.forEach(System.out::println);

        for (Cell cell : actualHeaders) {
            System.out.println(cell.getStringCellValue());

        }

        Cell requiredExcelHeader = getRequiredExcelHeader(actualHeaders, ExcelHeaderEnum.SERVICE_TYPE.getHeaderName());


//       1. pentru toate celulele executa asta
        while (rowIterator.hasNext()) {

            Row row = rowIterator.next();

            for (Cell cellFromRow : row) {

                if (cellFromRow.getColumnIndex() == requiredExcelHeader.getColumnIndex()) {
                    populateExcelMap(cellFromRow.getStringCellValue(), row);
                }
            }
        }

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