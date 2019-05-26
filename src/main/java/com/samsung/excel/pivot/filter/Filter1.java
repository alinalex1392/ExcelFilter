package com.samsung.excel.pivot.filter;

import com.samsung.excel.util.ExcelHeaderEnum;

import java.util.*;

public class Filter1 implements FilterEnumInterface {

//    This enum contains the headerEnumValue and the value that we want from that column to be filtered

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
