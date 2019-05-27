package com.samsung.excel.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum FilterValueEnum {

//    This enum contains the headerEnumValue and the value that we want from that column to be filtered

    FILTER1(ExcelHeaderEnum.SERVICE_TYPE, Arrays.asList("RH", "SR", "AD")),

    /* in functie de felul de pivot, aici trebuie un pivot cu InWarranty(IW) si unul
    pentru outOfWarranty(OW, NA, celule blank

      pivot outOfWarranty trebuie sa includa toate service type-urile
     */

    FILTER2(ExcelHeaderEnum.INOUTWTY, Arrays.asList("NA", "OW"));



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
