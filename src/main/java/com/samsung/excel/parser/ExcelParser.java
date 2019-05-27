package com.samsung.excel.parser;

import com.cookingfox.guava_preconditions.Preconditions;
import com.samsung.excel.filter.ExcelFilterUtil;
import com.samsung.excel.pivot.PivotConfig;
import com.samsung.excel.util.ExcelException;
import com.samsung.excel.util.ExcelHeaderEnum;
import com.samsung.excel.util.ExcelUtil;
import lombok.Data;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Data
public class ExcelParser {

    private static final Map<String, List<Row>> excel_by_service_name = new HashMap<>();

    private List<Cell> actualHeaders1;
    private ExcelFilterUtil excelFilterUtil = new ExcelFilterUtil();

    int numberOfHeaderCell = 0;


    public ExcelParser() {
    }

    public void processFile(File file, PivotConfig pivotConfig) throws IOException {

        Preconditions.checkNotNull(file, "File cannot be null while parsing");

        XSSFWorkbook workbook;

        try (InputStream fileInput = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fileInput);
        }

        createSheetForPivots(workbook, pivotConfig.getName());


        XSSFSheet mainSheet = workbook.getSheetAt(0);
        XSSFSheet pivotSheet = workbook.getSheetAt(1);


        Iterator<Row> rowIterator = mainSheet.iterator();

        Row header = rowIterator.next();

//        Get the total numbers of header so it can be used to construct the pivot
        numberOfHeaderCell = header.getLastCellNum();

        actualHeaders1 = ExcelUtil.getRequiredHeaders(header);


//        ******************Filter the sheet by some criteria

        Map<Integer, List<String>> filterMap = ExcelUtil.getFilterMap(actualHeaders1, pivotConfig);

        excelFilterUtil.eraseRows(mainSheet, filterMap);

        excelFilterUtil.writeSheetToSpecificFile(workbook, "SheetFiltered");


        System.out.println("########### Actual actualHeaders################");

        actualHeaders1.forEach(System.out::println);

        for (Cell cell : actualHeaders1) {
            System.out.println(cell.getStringCellValue());

        }


        createPivot(mainSheet, pivotSheet);

        excelFilterUtil.writeSheetToSpecificFile(workbook, "TestExample.xlsx");


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

        System.out.println("#################### ExcelParser Parsed ##################");

    }

    private void createSheetForPivots(Workbook workbook, String pivotName) {
        workbook.createSheet(pivotName);
    }

    private void createPivot(XSSFSheet sheet, XSSFSheet pivotSheet) {

        CellReference position = new CellReference(0, 0);

//        AreaReference areaReference = new AreaReference(position,
//                new CellReference(sheet.getLastRowNum(), actualHeaders1.size()), SpreadsheetVersion.EXCEL2007);


//        TODO actualHeaders1.size() needs to be the maximum cell index position not the size
        AreaReference areaReference = new AreaReference(position,
                new CellReference(sheet.getLastRowNum(), numberOfHeaderCell), SpreadsheetVersion.EXCEL2007);


        XSSFPivotTable pivotTable = pivotSheet.createPivotTable(areaReference, position, sheet);
//        XSSFPivotTable pivotTable = sheet.createPivotTable(areaReference, new CellReference(sheet.getLastRowNum() + 10, 1));

        pivotTable.addRowLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.ASC_NAME));
        pivotTable.addRowLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.SERVICE_TYPE));
        pivotTable.addRowLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.STATUS_TEXT));
        pivotTable.addRowLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.REASON_TEXT));
//        pivotTable.addRowLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.SERVICE_TYPE_TXT));
//        pivotTable.addRowLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.REASON_TEXT));

//        ExcelHeaderEnum.PENDING_DAYS.getAcceptedValues().sort(Comparator.reverseOrder());

        pivotTable.addColLabel(getColumnIndexOf(actualHeaders1, ExcelHeaderEnum.PENDING_DAYS));
//        pivotTable.getColLabelColumns().stream().sorted();

//        sortAscending(ExcelHeaderEnum.PENDING_DAYS.getAcceptedValues(), ExcelHeaderEnum.PENDING_DAYS);


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

    public void sortAscending(List<String> pendingDays, ExcelHeaderEnum days) {
        pendingDays.stream().sorted((o1, o2) -> Integer.valueOf(o2) - Integer.valueOf(o1));

    }


}