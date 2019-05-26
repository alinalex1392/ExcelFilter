package com.samsung.excel.pivot.filter;

import com.samsung.excel.util.ExcelHeaderEnum;

import java.util.List;
import java.util.Map;

public interface FilterEnumInterface {


    Map<ExcelHeaderEnum, List<String>> getFilterMap();
}
