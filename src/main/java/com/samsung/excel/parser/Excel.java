package com.samsung.excel.parser;

import com.cookingfox.guava_preconditions.Preconditions;
import com.samsung.excel.util.ExcelUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Data
@Slf4j
public class Excel {
    //    private static List<Pair<Integer, Cell>> actualHeaders = new ArrayList<Pair<Integer, Cell>>();

    private static final Map<Integer, ArrayList<Cell>> ROW_LIST_MAP = new HashMap<>();
    private static XSSFRow row;
    private static ArrayList<Cell> actualHeaders;
    private static Map<Row, List<Cell>> excel = new HashMap<>();
    private static Map<String, ArrayList<Row>> SO_BY_PARTNER = new HashMap<>();

    public Excel() {
    }

    public void parseFile(File file) throws IOException {

        Preconditions.checkNotNull(file, "File cannot be null while parsing");
        log.info("Started parsing input file ");
        XSSFWorkbook workbook;

        try (FileInputStream fileInput = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fileInput);
        }


        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Row header = rowIterator.next();
        List<Cell> actualHeaders = ExcelUtil.getRequiredHeaders(header);
        ROW_LIST_MAP.put(0, (ArrayList<Cell>) actualHeaders);
        System.out.println(" Actual actualHeaders");

//        actualHeaders.forEach(System.out::println);

//        for (Cell cell : actualHeaders) {
//            System.out.println(cell.getStringCellValue());
//
//        }

//       1. pentru toate celulele executa asta
        while (rowIterator.hasNext()) {

            Row next = rowIterator.next();
            ROW_LIST_MAP.put(next.getRowNum(), new ArrayList<>());

            for (Cell actualHeader : actualHeaders) {
                ROW_LIST_MAP.get(next.getRowNum()).add(next.getCell(actualHeader.getColumnIndex()));
//               ROW_LIST_MAP.put(next.getRowNum(), rowIterator.hasNext(actualHeader.getColumnIndex()));
//               ROW_LIST_MAP.put(next.getRowNum(), next.getCell(actualHeader.getColumnIndex()));
            }
        }
//        aici filtreaza ROW_LIST_MAP ca sa contina doar randurile dorite
        ROW_LIST_MAP.forEach((key, value) -> System.out.println(key + "\t"  + value));

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


}