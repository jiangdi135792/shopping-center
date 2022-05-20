package com.ccut.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<demoDate> {
    //一行一行读取内容
    @Override
    public void invoke(demoDate demoDate, AnalysisContext analysisContext) {
        System.out.println("****"+demoDate);
    }
    //读取表头
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }
//读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
