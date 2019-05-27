package com.samsung.excel.pivot.filter;

import com.samsung.excel.util.ExcelHeaderEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterPivotClasic implements FilterEnumInterface {

    private Map<ExcelHeaderEnum, List<String>> filterMap = new HashMap<ExcelHeaderEnum, List<String>>(){

        {
            this.put(ExcelHeaderEnum.SERVICE_TYPE, Arrays.asList("PS", "CI"));
        }

    } ;

    @Override
    public Map<ExcelHeaderEnum, List<String>> getFilterMap() {
        return filterMap;
    }

}
