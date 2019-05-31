package com.samsung.excel.pivot;

import com.samsung.excel.pivot.filter.Filter;
import com.samsung.excel.util.ExcelHeaderEnum;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;

import java.util.List;

public interface PivotConfig {

    String getName();

    Filter getFilter();

    List<ExcelHeaderEnum> getPivotRowLabels();

    List<ExcelHeaderEnum> getPivotColumnLabels();

    DataConsolidateFunction getDataConsolidateFunction();

    ExcelHeaderEnum getColumnForDataConsolidateFunction();

    String outputFilename();

}
