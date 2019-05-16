package com.samsung.excel.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum FilterValueEnum {

//    This enum contains the headerEnumValue and the value that we want from that column to be filtered

    FILTER1(ExcelHeaderEnum.SERVICE_TYPE, Arrays.asList("PS", "CI"));

    ExcelHeaderEnum headerEnum;
    List<String> value;

    private FilterValueEnum(ExcelHeaderEnum excelHeaderEnum, List<String> filterValue) {

        this.headerEnum = excelHeaderEnum;
        this.value = filterValue;
    }


    public ExcelHeaderEnum getHeaderEnum() {
        return headerEnum;
    }

    public void setHeaderEnum(ExcelHeaderEnum headerEnum) {
        this.headerEnum = headerEnum;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
