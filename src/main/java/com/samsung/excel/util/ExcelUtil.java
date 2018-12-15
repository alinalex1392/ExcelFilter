package com.samsung.excel.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    private static final List<String> headersWanted = ExcelHeadersEnum.getAllHeadersName();

    public static List<Cell> getRequiredHeaders(Row header) {

        List<Cell> actualHeaders = new ArrayList<>();
        for (Cell cell : header) {

            if (headersWanted.contains(cell.getStringCellValue())) {
                actualHeaders.add(cell);
            }
        }
        return actualHeaders;
    }
}
