package com.samsung.excel.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

@Data
@AllArgsConstructor
public class ExcelConstructUtil {

    private  List<Cell> headersWanted;
    private int maxColumnIndex;
}
