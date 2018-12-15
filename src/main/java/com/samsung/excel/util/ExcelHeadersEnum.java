package com.samsung.excel.util;

import com.cookingfox.guava_preconditions.Preconditions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ExcelHeadersEnum {

    //TODO aici Mircea trebuie sa pui ce headere iti doresti
    // si de asemenea cu ce valori vrei sa fie populate coloanele
    // respective deoarece ai zis ca faci o filtrare pe coloane in functie de nu stiu ce buisness bla bla
    HEADER1("HEADER1", Arrays.asList()),
    HEADER2("HEADER1", Arrays.asList()),
    HEADER3("HEADER1", Arrays.asList()),
    HEADER4("HEADER1", Arrays.asList()),
    HEADER5("HEADER1", Arrays.asList()),
    HEADER6("HEADER1", Arrays.asList()),
    HEADER7("HEADER1", Arrays.asList());

    private String headerName;
    private List<String> acceptedValues;

    ExcelHeadersEnum(String header) {
        this.headerName = header;
    }

    ExcelHeadersEnum(String header, List<String> acceptedValues) {
        this.headerName = header;
        this.acceptedValues = acceptedValues;
    }

    public static List<String> getAllHeadersName() {

        return Arrays.stream(ExcelHeadersEnum.values())
                .map(excelHeadersEnum -> excelHeadersEnum.headerName)
                .collect(Collectors.toList());
    }

    public static List<String> getAcceptedValuesByHeader(String headerName) {

        Preconditions.checkNotNull(headerName, "Header Name cannot be null");

        ExcelHeadersEnum excelHeadersEnum1 = ExcelHeadersEnum.valueOf(headerName);

        Preconditions.checkNotNull(excelHeadersEnum1,
                "Value of headerName need to be in ExcelHeadersEnum ");

        return excelHeadersEnum1.getAcceptedValues();
    }

    public List<String> getAcceptedValues() {
        return acceptedValues;
    }

}
