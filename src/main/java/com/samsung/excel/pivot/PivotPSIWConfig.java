package com.samsung.excel.pivot;

import com.samsung.excel.pivot.filter.FilterEnumInterface;
import com.samsung.excel.pivot.filter.FilterPivotClasic;

public class PivotPSIWConfig implements PivotConfig {

    @Override
    public String getName() {
        return "PendingStatus(IW)";
    }

    @Override
    public FilterEnumInterface getFilter() {
        return new FilterPivotClasic();
    }


}
