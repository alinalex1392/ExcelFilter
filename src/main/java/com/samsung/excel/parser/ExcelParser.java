package com.samsung.excel.parser;

import com.cookingfox.guava_preconditions.Preconditions;
import com.samsung.excel.pivot.PivotConfig;
import com.samsung.excel.util.ExcelConstructUtil;
import com.samsung.excel.util.ExcelHeaderEnum;
import com.samsung.excel.util.ExcelUtil;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExcelParser {

    private ExcelUtil excelFilterUtil = new ExcelUtil();

    private ExcelConstructUtil excelConstructUtil;

    public ExcelParser() {
    }

    public void processFile(File file, PivotConfig pivotConfig) throws IOException {

        Preconditions.checkNotNull(file, "File cannot be null while parsing");
        Preconditions.checkNotNull(pivotConfig, "The pivot configuration cannot be null");

        XSSFWorkbook workbook;

        try (InputStream fileInput = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fileInput);
        }

        XSSFSheet mainSheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = mainSheet.iterator();

        Row header = rowIterator.next();


        excelConstructUtil = com.samsung.excel.util.ExcelUtil.getExcelConstructUtil(header);

//        ******************Filter the sheet by some criteria

        Map<Integer, List<String>> filterMap = com.samsung.excel.util.ExcelUtil.getFilterMap(excelConstructUtil.getHeadersWanted(), pivotConfig);

        excelFilterUtil.eraseRows(mainSheet, filterMap);


        XSSFWorkbook newWb = copySheet(workbook.getSheetAt(0));

        createSheetForPivots(newWb, pivotConfig.getName());


        excelFilterUtil.writeSheetToSpecificFile(workbook, "SheetFiltered");
        excelFilterUtil.writeSheetToSpecificFile(newWb, "SheetFilteredProcessed");


        System.out.println("########### Actual actualHeaders################");

        excelConstructUtil.getHeadersWanted().forEach(System.out::println);

        for (Cell cell : excelConstructUtil.getHeadersWanted()) {
            System.out.println(cell.getStringCellValue());

        }


        createPivot(newWb.getSheetAt(0), newWb.getSheetAt(1), pivotConfig);

        excelFilterUtil.writeSheetToSpecificFile(newWb, "TestExample.xlsx");

        System.out.println("#################### ExcelParser Parsed ##################");

    }


//    This method is copying sheet without erased rows

    private XSSFWorkbook copySheet(XSSFSheet sheetAt) {
        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet rawData = wb.createSheet("RawData");

        ExcelUtil.copySheet(sheetAt, rawData);

        return wb;
    }

    private void createSheetForPivots(Workbook workbook, String pivotName) {
        workbook.createSheet(pivotName);
    }

    private void createPivot(XSSFSheet sheet, XSSFSheet pivotSheet, PivotConfig pivotConfig) {

        CellReference position = new CellReference(0, 0);

//        AreaReference areaReference = new AreaReference(position,
//                new CellReference(sheet.getLastRowNum(), excelHeaders.size()), SpreadsheetVersion.EXCEL2007);

        AreaReference areaReference = new AreaReference(position,
                new CellReference(sheet.getLastRowNum(), excelConstructUtil.getMaxColumnIndex()), SpreadsheetVersion.EXCEL2007);


        XSSFPivotTable pivotTable = pivotSheet.createPivotTable(areaReference, position, sheet);
//        XSSFPivotTable pivotTable = sheet.createPivotTable(areaReference, new CellReference(sheet.getLastRowNum() + 10, 1));


        List<ExcelHeaderEnum> pivotRowLabels = pivotConfig.getPivotRowLabels();

        for (ExcelHeaderEnum rowLabels : pivotRowLabels) {
            int columnIndexOf = getColumnIndexOf(excelConstructUtil.getHeadersWanted(), rowLabels);

            if (columnIndexOf < 0) {

                throw new RuntimeException("Column for the pivot rowLabel must exist in the header of the sheet");
            }

            pivotTable.addRowLabel(columnIndexOf);
        }

//        pivotTable.addRowLabel(getColumnIndexOf(excelConstructUtil.getHeadersWanted(), ExcelHeaderEnum.ASC_NAME));
//        pivotTable.addRowLabel(getColumnIndexOf(excelConstructUtil.getHeadersWanted(), ExcelHeaderEnum.SERVICE_TYPE));
//        pivotTable.addRowLabel(getColumnIndexOf(excelConstructUtil.getHeadersWanted(), ExcelHeaderEnum.STATUS_TEXT));
//        pivotTable.addRowLabel(getColumnIndexOf(excelConstructUtil.getHeadersWanted(), ExcelHeaderEnum.REASON_TEXT));

//        pivotTable.addRowLabel(getColumnIndexOf(excelHeaders, ExcelHeaderEnum.SERVICE_TYPE_TXT));
//        pivotTable.addRowLabel(getColumnIndexOf(excelHeaders, ExcelHeaderEnum.REASON_TEXT));

//        ExcelHeaderEnum.PENDING_DAYS.getAcceptedValues().sort(Comparator.reverseOrder());


        List<ExcelHeaderEnum> pivotColumnLabels = pivotConfig.getPivotColumnLabels();

        for (ExcelHeaderEnum columnLabels : pivotColumnLabels) {
            int columnIndexOf = getColumnIndexOf(excelConstructUtil.getHeadersWanted(), columnLabels);

            if (columnIndexOf < 0) {

                throw new RuntimeException("Column for the pivot columnLabel must exist in the header of the sheet");
            }
            pivotTable.addColLabel(columnIndexOf);

        }

//        pivotTable.addColLabel(getColumnIndexOf(excelConstructUtil.getHeadersWanted(), ExcelHeaderEnum.PENDING_DAYS));
//        pivotTable.getColLabelColumns().stream().sorted();

//        sortAscending(ExcelHeaderEnum.PENDING_DAYS.getAcceptedValues(), ExcelHeaderEnum.PENDING_DAYS);


        pivotTable.addColumnLabel(pivotConfig.getDataConsolidateFunction(), getColumnIndexOf(excelConstructUtil.getHeadersWanted(), pivotConfig.getColumnForDataConsolidateFunction()));


//        pivotTable.addReportFilter(getColumnIndexOf(excelHeaders, ExcelHeaderEnum.SERVICE_TYPE_TXT));

    }

    private int getColumnIndexOf(List<Cell> actualHeaders, ExcelHeaderEnum headerDef) {

        Optional<Cell> first = actualHeaders.stream()
                .filter(cell -> cell.getStringCellValue().equals(headerDef.getHeaderName()))
                .findFirst();


        return first.map(Cell::getColumnIndex).orElse(-1);
    }


}