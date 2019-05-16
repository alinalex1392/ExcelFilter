package com.samsung.excel.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.*;

public class ExcelUtil {

    private static final List<String> headersWanted = ExcelHeaderEnum.getAllHeadersName();

    public static List<Cell> getRequiredHeaders(Row header) {

        List<Cell> actualHeaders = new ArrayList<>();
        for (Cell cell : header) {

            if (headersWanted.contains(cell.getStringCellValue())) {
                actualHeaders.add(cell);
            }
        }
        return actualHeaders;
    }

    public static Optional<Cell> getRequiredHeaderByName(List<Cell> header, String headerName) {

        for (Cell headerCell : header) {

            if (headerCell.getStringCellValue().equals(headerName)) {
                return Optional.of(headerCell);
            }
        }

        return Optional.empty();
    }


//        This return a map that has the following form
//        The key of the map is the column that contain the value that need to be filtered


//               columnNumber          Values to be filtered
//         Map :  1             ->        {'rh','it'}
//


    public static Map<Integer, List<String>> getFilterMap(List<Cell> headers){
        FilterValueEnum[] values = FilterValueEnum.values();

        Map<Integer, List<String>> filterMap = new HashMap<>();

        for(FilterValueEnum filterValueEnum : values){
            for(Cell cell : headers){
                if(cell.getStringCellValue().trim().equals(filterValueEnum.getHeaderEnum().getHeaderName())){
                    int columnIndex = cell.getColumnIndex();
                    filterMap.put(columnIndex, filterValueEnum.getValue());
                    break;
                }

            }
        }

        return filterMap;
    }
}