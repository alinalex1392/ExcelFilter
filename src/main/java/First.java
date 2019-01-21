import com.samsung.excel.parser.Excel;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class First {

    private static final String FILENAME = "SEROM.XLSX";
    private static final String TestFILENAME = "SEROMTest.XLSX";
    private Excel excelObject = new Excel();

    public static void main(String[] args) {


        try {
            readFile();

        } catch (IOException ex) {
            System.out.println("File not found.");
        }

    }

    private static void readFile() throws IOException {

        First mainObject = new First();
        File file = new File(Objects.requireNonNull(First.class.getClassLoader().getResource(TestFILENAME)).getFile());
        mainObject.excelObject.parseFile(file);


//        File file = new File("C:\\Users\\mircea.chesca\\Desktop\\PendingWithJava\\SEROM.XLSX");
//        FileInputStream fileInput = new FileInputStream(file);
//        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
//
//        fileInput.close();
//
//
//        if (file.isFile() && file.exists()) {
//            System.out.println("Raw data file open successfully.");
//        } else {
//            System.out.println("Error to open raw data file.");
//        }
//
//        XSSFSheet spreadsheet = workbook.getSheetAt(0);
//        Iterator<Row> rowIterator = spreadsheet.iterator();
//
//
//        Row header = rowIterator.next();
//
//        for (Cell cell : header) {
//
////            System.out.println("Cell number: " + cell.getColumnIndex() + " value: " +
////                    cell.getStringCellValue());
//            if (columnsWanted.contains(cell.getStringCellValue())) {
//                headers.add(cell);
//            }
//        }
//
//        System.out.println(" Actual headers");
//
//        for (Cell cell : headers) {
//
//            System.out.println(cell.getStringCellValue());
//
//
//        }
//
//
////       1. pentru toate celulele executa asta
//        while (rowIterator.hasNext()) {
//
//            Row next = rowIterator.next();
//            ROW_LIST_MAP.put(next.getRowNum(), new ArrayList<Cell>());
//
//            for (Cell actualHeader : headers) {
//                ROW_LIST_MAP.get(next.getRowNum()).add(next.getCell(actualHeader.getColumnIndex()));
////                ROW_LIST_MAP.put(next.getRowNum(), rowIterator.hasNext(actualHeader.getColumnIndex()));
//
////                ROW_LIST_MAP.put(next.getRowNum(), next.getCell(actualHeader.getColumnIndex()));
//            }
//
//
//        }
////        aici filtreaza ROW_LIST_MAP ca sa contina doar randurile dorite
//
//        for (Row row : spreadsheet) {
//            for (Cell cell : row) {
//                if (headers.indexOf(cell) ==  row.cellIterator().next().getColumnIndex()) {
//                    ArrayList<Cell> excelRows = new ArrayList<Cell>();
//                    excelRows.add(cell);
//
//                }
//            }
//        }
//
//
////      finish 1
//
//
//        System.exit(0);
//
//
////        spreadsheet.createFreezePane(0, 1);
//
////        for (int i = 1; i < 200; i++) {
////            spreadsheet.autoSizeColumn(i);
////        }
//
//
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
////        fileInput.close();
    }
}
