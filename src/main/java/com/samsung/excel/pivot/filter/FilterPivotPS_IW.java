package com.samsung.excel.pivot.filter;

import com.samsung.excel.util.ExcelHeaderEnum;

import java.util.*;

public class FilterPivotPS_IW implements Filter {

    private Map<ExcelHeaderEnum, List<String>> filterMap = new HashMap<ExcelHeaderEnum, List<String>>(){

        {
            this.put(ExcelHeaderEnum.INOUTWTY, Arrays.asList("OW", "NA"));
            this.put(ExcelHeaderEnum.SERVICE_TYPE, Arrays.asList("RH", "AD", "SR"));
            this.put(ExcelHeaderEnum.CIC_PRD,
                    Arrays.asList("WSM", "HKE", "REF",
                                  "VDE", "MON", "ACN",
                                  "CLE", "VEE"));
        }

    } ;

    @Override
    public Map<ExcelHeaderEnum, List<String>> getFilterMap() {
        return filterMap;
    }
}
