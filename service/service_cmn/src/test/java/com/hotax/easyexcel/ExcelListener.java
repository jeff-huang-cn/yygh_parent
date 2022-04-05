package com.hotax.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author: Jeff 2022-04-04 14:35
 * @description:
 **/
public class ExcelListener extends AnalysisEventListener<UserData> {
    @Override
    public void invoke(UserData o, AnalysisContext analysisContext) {
        System.out.println(o);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息：" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
