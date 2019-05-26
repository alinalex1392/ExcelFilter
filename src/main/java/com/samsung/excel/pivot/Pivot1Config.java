package com.samsung.excel.pivot;

import com.samsung.excel.pivot.filter.FilterEnumInterface;
import com.samsung.excel.pivot.filter.Filter1;

public class Pivot1Config implements PivotConfig {

    @Override
    public String getName() {
        return "PIVOT1_NAME";
    }

    @Override
    public FilterEnumInterface getFilter() {
        return new Filter1();
    }


}
