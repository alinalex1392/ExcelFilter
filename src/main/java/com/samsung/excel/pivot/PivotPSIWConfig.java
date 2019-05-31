package com.samsung.excel.pivot;

import com.samsung.excel.pivot.filter.Filter;
import com.samsung.excel.pivot.filter.FilterPivotPSIW;
import com.samsung.excel.util.ExcelHeaderEnum;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PivotPSIWConfig implements PivotConfig {

    @Override
    public String getName() {
        return "PendingStatus(IW)";
    }

    @Override
    public Filter getFilter() {
        return new FilterPivotPSIW();
    }

    @Override
    public List<ExcelHeaderEnum> getPivotRowLabels() {
        return Arrays.asList(ExcelHeaderEnum.ASC_NAME,
                ExcelHeaderEnum.SERVICE_TYPE,
                ExcelHeaderEnum.STATUS_TEXT,
                ExcelHeaderEnum.REASON_TEXT);
    }

    @Override
    public List<ExcelHeaderEnum> getPivotColumnLabels() {
        return Collections.singletonList(ExcelHeaderEnum.PENDING_DAYS);
    }

    @Override
    public DataConsolidateFunction getDataConsolidateFunction() {
        return DataConsolidateFunction.COUNT;
    }

    @Override
    public ExcelHeaderEnum getColumnForDataConsolidateFunction() {
        return ExcelHeaderEnum.Service_order;
    }

    @Override
    public String outputFilename() {
        return getName();
    }


}
