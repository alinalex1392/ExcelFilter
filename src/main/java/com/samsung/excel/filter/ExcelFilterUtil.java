package com.samsung.excel.filter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilterColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelFilterUtil {

    public void filter(XSSFSheet my_sheet, int columnID, List<String> values) {

        //        This part only add filters on the xml
   /* Step-1: Get the CTAutoFilter Object */
        CTAutoFilter sheetFilter = my_sheet.getCTWorksheet().getAutoFilter();

/* Step -2: Add new Filter Column */
        CTFilterColumn myFilterColumn = sheetFilter.insertNewFilterColumn(0);
/* Step-3: Set Filter Column ID */
        myFilterColumn.setColId(columnID);
/* Step-4: Add Multiple Filters on a Single Column */
        CTFilters listofFilters = myFilterColumn.addNewFilters();
        CTFilter myFilter1 = listofFilters.addNewFilter();
        CTFilter myFilter2 = listofFilters.addNewFilter();
/* Step-5: Define Multiple Filters */
        myFilter1.setVal("A");  // Filter by Both A and B
        myFilter2.setVal("B");
/* Add this to a list for comparison */
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");


//        This part sort the data from the sheet by a specific filter
        XSSFRow r1;
        /* Step-6: Loop through Rows and Apply Filter */
        for (Row r : my_sheet) {
            for (Cell c : r) {
                if (c.getColumnIndex() == 1 && !list.contains(c.getStringCellValue())) {
                    r1 = (XSSFRow) c.getRow();
                    if (r1.getRowNum() != 0) { /* Ignore top row */
                                /* Hide Row that does not meet Filter Criteria */
                        r1.getCTRow().setHidden(true);
                    }
                }
            }
        }

    }

    public void hideData(Sheet my_sheet, int columnIndex, List<String> acceptedValues) {

        XSSFRow r1;
        /* Step-6: Loop through Rows and Apply Filter */
        for (Row r : my_sheet) {
            for (Cell c : r) {
                if (c.getColumnIndex() == columnIndex && !acceptedValues.contains(c.getStringCellValue())) {
                    r1 = (XSSFRow) c.getRow();
                    if (r1.getRowNum() != 0) { /* Ignore top row */
                                /* Hide Row that does not meet Filter Criteria */
                        r1.getCTRow().setHidden(true);
                    }
                }
            }
        }
    }

    public List<Row> getRowsForErase(Sheet sheet, Map<Integer, List<String>> filterMap) {

        List<Row> rowIdsForRemove = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.rowIterator();

//        skip past header
        rowIterator.next();

        for ( ;rowIterator.hasNext(); ) {
            Row row = rowIterator.next();


//            TODO de urmarit
            finished :  for (Cell cell : row) {
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

    public void eraseRows(Sheet sheet, Map<Integer, List<String>> filterMap){

        getRowsForErase(sheet, filterMap).forEach(sheet::removeRow);

    }

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
