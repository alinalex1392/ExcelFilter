package com.samsung.excel.util;

import com.cookingfox.guava_preconditions.Preconditions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum ExcelHeaderEnum {

    //TODO sterge a doua coloana cea cu Collections.singletonList la toate si dupa si variabila      private List<String> acceptedValues;
    // pe unde o vezi
    Service_order("Service_order", Collections.singletonList("Service_order")),
    HEADER2("HQ aging base date", Collections.singletonList("HQ aging base date")),
    PENDING_DAYS("PENDING_DAYS", Collections.singletonList("PENDING_DAYS")),
    POSTING_DATE("POSTING_DATE", Collections.singletonList("POSTING_DATE")),
    SERVICE_TYPE("SERVICE_TYPE", Collections.singletonList("SERVICE_TYPE")),
    SERVICE_TYPE_TXT("SERVICE_TYPE_TXT", Collections.singletonList("SERVICE_TYPE_TXT")),
    STATUS("STATUS", Collections.singletonList("STATUS")),
    STATUS_TEXT("STATUS_TEXT", Collections.singletonList("STATUS_TEXT")),
    REASON("REASON", Collections.singletonList("REASON")),
    REASON_TEXT("REASON_TEXT", Collections.singletonList("REASON_TEXT")),
    REASON_AGING("REASON_AGING", Collections.singletonList("REASON_AGING")),
    HEADER12("ASC code", Collections.singletonList("ASC code")),
    HEADER13("ASC name", Collections.singletonList("ASC name")),
    HEADER14("Engineer code", Collections.singletonList("Engineer code")),
    HEADER15("Engineer name", Collections.singletonList("Engineer name")),
    ASC_JOB_NO("ASC_JOB_NO", Collections.singletonList("ASC_JOB_NO")),
    MODEL("MODEL", Collections.singletonList("MODEL")),
    CIC_PRD("CIC_PRD", Collections.singletonList("CIC_PRD")),
    INOUTWTY("INOUTWTY", Collections.singletonList("INOUTWTY")),
    DEFECT_DESC("DEFECT_DESC", Collections.singletonList("DEFECT_DESC")),
    Part_code1("Part_code1", Collections.singletonList("Part_code1")),
    confirmation_No1("confirmation_No1", Collections.singletonList("confirmation_No1")),
    QTY1("QTY1", Collections.singletonList("QTY1")),
    PO_DATE1("PO_DATE1", Collections.singletonList("PO_DATE1")),
    SO_SHIP_DATE1("SO_SHIP_DATE1", Collections.singletonList("SO_SHIP_DATE1")),
    SO_ARRIVE_DATE1("SO_ARRIVE_DATE1", Collections.singletonList("SO_ARRIVE_DATE1")),
    SO_UPS_TRK_NO1("SO_UPS_TRK_NO1", Collections.singletonList("SO_UPS_TRK_NO1")),
    SO_STATUS_TEXT1("SO_STATUS_TEXT1", Collections.singletonList("SO_STATUS_TEXT1")),
    Part_code2("Part_code2", Collections.singletonList("Part_code2")),
    SO_NO2("SO_NO2", Collections.singletonList("SO_NO2")),
    QTY2("QTY2", Collections.singletonList("QTY2")),
    PO_DATE2("PO_DATE2", Collections.singletonList("PO_DATE2")),
    HSO_SHIP_DATE2("SO_SHIP_DATE2", Collections.singletonList("SO_SHIP_DATE2")),
    SO_ARRIVE_DATE2("SO_ARRIVE_DATE2", Collections.singletonList("SO_ARRIVE_DATE2")),
    SO_UPS_TRK_NO2("SO_UPS_TRK_NO2", Collections.singletonList("SO_UPS_TRK_NO2")),
    SO_STATUS_TEXT2("SO_STATUS_TEXT2", Collections.singletonList("SO_STATUS_TEXT2")),
    Part_code3("Part_code3", Collections.singletonList("Part_code3")),
    SO_NO3("SO_NO3", Collections.singletonList("SO_NO3")),
    QTY3("QTY3", Collections.singletonList("QTY3")),
    PO_DATE3("PO_DATE3", Collections.singletonList("PO_DATE3")),
    SO_SHIP_DATE3("SO_SHIP_DATE3", Collections.singletonList("SO_SHIP_DATE3")),
    SO_ARRIVE_DATE3("SO_ARRIVE_DATE3", Collections.singletonList("SO_ARRIVE_DATE3")),
    SO_UPS_TRK_NO3("SO_UPS_TRK_NO3", Collections.singletonList("SO_UPS_TRK_NO3")),
    SO_STATUS_TEXT3("SO_STATUS_TEXT3", Collections.singletonList("SO_STATUS_TEXT3")),
    Part_code4("Part_code4", Collections.singletonList("Part_code4")),
    SO_NO4("SO_NO4", Collections.singletonList("SO_NO4")),
    QTY4("QTY4", Collections.singletonList("QTY4")),
    PO_DATE4("PO_DATE4", Collections.singletonList("PO_DATE4")),
    SO_SHIP_DATE4("SO_SHIP_DATE4", Collections.singletonList("SO_SHIP_DATE4")),
    SO_ARRIVE_DATE4("SO_ARRIVE_DATE4", Collections.singletonList("SO_ARRIVE_DATE4")),
    SO_UPS_TRK_NO4("SO_UPS_TRK_NO4", Collections.singletonList("SO_UPS_TRK_NO4")),
    SO_STATUS_TEXT4("SO_STATUS_TEXT4", Collections.singletonList("SO_STATUS_TEXT4")),
    Part_code5("Part_code5", Collections.singletonList("Part_code5")),
    SO_NO5("SO_NO5", Collections.singletonList("SO_NO5")),
    QTY5("QTY5", Collections.singletonList("QTY5")),
    PO_DATE5("PO_DATE5", Collections.singletonList("PO_DATE5")),
    SO_SHIP_DATE5("SO_SHIP_DATE5", Collections.singletonList("SO_SHIP_DATE5")),
    SO_ARRIVE_DATE5("SO_ARRIVE_DATE5", Collections.singletonList("SO_ARRIVE_DATE5")),
    SO_UPS_TRK_NO5("SO_UPS_TRK_NO5", Collections.singletonList("SO_UPS_TRK_NO5")),
    SO_STATUS_TEXT5("SO_STATUS_TEXT5", Collections.singletonList("SO_STATUS_TEXT5")),
    SAW_NO("SAW_NO", Collections.singletonList("SAW_NO")),
    HEADER62("1ST_SAW_CRDAT", Collections.singletonList("1ST_SAW_CRDAT"));

    private String headerName;
    private List<String> acceptedValues;

    ExcelHeaderEnum(String header, List<String> acceptedValues) {
        this.headerName = header;
        this.acceptedValues = acceptedValues;
    }

    public static List<String> getAllHeadersName() {

        return Arrays.stream(ExcelHeaderEnum.values())
                .map(excelHeaderEnum -> excelHeaderEnum.headerName)
                .collect(Collectors.toList());
    }

    public static List<String> getAcceptedValuesByHeader(String headerName) {

        Preconditions.checkNotNull(headerName, "Header Name cannot be null");

        ExcelHeaderEnum excelHeaderEnum1 = ExcelHeaderEnum.valueOf(headerName);


        Preconditions.checkNotNull(excelHeaderEnum1,
                "Value of headerName need to be in ExcelHeaderEnum ");

        return excelHeaderEnum1.getAcceptedValues();
    }

    public String getHeaderName() {
        return headerName;
    }

    public List<String> getAcceptedValues() {
        return acceptedValues;
    }

}