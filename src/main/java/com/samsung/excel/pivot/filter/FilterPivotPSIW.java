package com.samsung.excel.pivot.filter;

import com.samsung.excel.util.ExcelHeaderEnum;

import java.util.*;

public class FilterPivotPSIW implements Filter {

    private Map<ExcelHeaderEnum, List<String>> filterMap = new HashMap<ExcelHeaderEnum, List<String>>(){

        {
            this.put(ExcelHeaderEnum.SERVICE_TYPE, Collections.singletonList(""));
        }

    } ;

    @Override
    public Map<ExcelHeaderEnum, List<String>> getFilterMap() {
        return filterMap;
    }

}
