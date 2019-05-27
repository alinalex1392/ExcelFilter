package com.samsung.excel.util;

import com.cookingfox.guava_preconditions.Preconditions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum ExcelHeaderEnum {

    //TODO sterge a doua coloana cea cu Collections.singletonList la toate si dupa si variabila      private List<String> acceptedValues;
    // pe unde o vezi
    Service_order("Service_order"),
    HEADER1("Error Status"),
    HEADER2("Error msg"),
    HEADER3("SAW Status"),
    HEADER4("SAW cat."),
    COUNTRY("COUNTRY"),
    HEADER5("HQ aging base date"),
    PENDING_DAYS("PENDING_DAYS"),
    POSTING_DATE("POSTING_DATE"),
    SERVICE_TYPE("SERVICE_TYPE"),
    SERVICE_TYPE_TXT("SERVICE_TYPE_TXT"),
    STATUS_TEXT("STATUS_TEXT"),
    REASON_TEXT("REASON_TEXT"),
    REASON_AGING("REASON_AGING"),
    HEADER12("ASC code"),
    ASC_NAME("ASC name"),
    HEADER14("Engineer code"),
    HEADER15("Engineer name"),
    ASC_JOB_NO("ASC_JOB_NO"),
    MODEL("MODEL"),
    CIC_PRD("CIC_PRD"),
    SERIAL_NO("SERIAL_NO"),
    INOUTWTY("INOUTWTY"),
    IMEI("IMEI"),
    DEFECT_DESC("DEFECT_DESC"),
    Part_code1("Part_code1"),
    ASC_PO_No1("ASC_PO_No1"),
    confirmation_No1("confirmation_No1"),
    QTY1("QTY1"),
    PO_DATE1("PO_DATE1"),
    SO_SHIP_DATE1("SO_SHIP_DATE1"),
    SO_ARRIVE_DATE1("SO_ARRIVE_DATE1"),
    SO_UPS_TRK_NO1("SO_UPS_TRK_NO1"),
    SO_STATUS_TEXT1("SO_STATUS_TEXT1"),
    Part_code2("Part_code2"),
    ASC_PO_No2("ASC_PO_No2"),
    SO_NO2("SO_NO2"),
    QTY2("QTY2"),
    PO_DATE2("PO_DATE2"),
    SO_SHIP_DATE2("SO_SHIP_DATE2"),
    SO_ARRIVE_DATE2("SO_ARRIVE_DATE2"),
    SO_UPS_TRK_NO2("SO_UPS_TRK_NO2"),
    SO_STATUS_TEXT2("SO_STATUS_TEXT2"),
    Part_code3("Part_code3"),
    ASC_PO_No3("ASC_PO_No3"),
    SO_NO3("SO_NO3"),
    QTY3("QTY3"),
    PO_DATE3("PO_DATE3"),
    SO_SHIP_DATE3("SO_SHIP_DATE3"),
    SO_ARRIVE_DATE3("SO_ARRIVE_DATE3"),
    SO_UPS_TRK_NO3("SO_UPS_TRK_NO3"),
    SO_STATUS_TEXT3("SO_STATUS_TEXT3"),
    Part_code4("Part_code4"),
    ASC_PO_No4("ASC_PO_No4"),
    SO_NO4("SO_NO4"),
    QTY4("QTY4"),
    PO_DATE4("PO_DATE4"),
    SO_SHIP_DATE4("SO_SHIP_DATE4"),
    SO_ARRIVE_DATE4("SO_ARRIVE_DATE4"),
    SO_UPS_TRK_NO4("SO_UPS_TRK_NO4"),
    SO_STATUS_TEXT4("SO_STATUS_TEXT4"),
    Part_code5("Part_code5"),
    ASC_PO_No5("ASC_PO_No5"),
    SO_NO5("SO_NO5"),
    QTY5("QTY5"),
    PO_DATE5("PO_DATE5"),
    SO_SHIP_DATE5("SO_SHIP_DATE5"),
    SO_ARRIVE_DATE5("SO_ARRIVE_DATE5"),
    SO_UPS_TRK_NO5("SO_UPS_TRK_NO5"),
    SO_STATUS_TEXT5("SO_STATUS_TEXT5");


    private String headerName;


    ExcelHeaderEnum(String header) {
        this.headerName = header;

    }

    public static List<String> getAllHeadersName() {

        return Arrays.stream(ExcelHeaderEnum.values())
                .map(excelHeaderEnum -> excelHeaderEnum.headerName)
                .collect(Collectors.toList());
    }

//    public static List<String> getAcceptedValuesByHeader(String headerName) {
////
////        Preconditions.checkNotNull(headerName, "Header Name cannot be null");
////
////        ExcelHeaderEnum excelHeaderEnum1 = ExcelHeaderEnum.valueOf(headerName);
////
////
////        Preconditions.checkNotNull(excelHeaderEnum1,
////                "Value of headerName need to be in ExcelHeaderEnum ");
////
////        return excelHeaderEnum1.getAcceptedValues();
////    }

    public String getHeaderName() {
        return headerName;
    }



}