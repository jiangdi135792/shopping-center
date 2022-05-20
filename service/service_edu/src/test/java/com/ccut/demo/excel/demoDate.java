package com.ccut.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data

public class demoDate {
    @ExcelProperty("学生编号")
    private Integer son;
    @ExcelProperty("学生姓名")
    private String sname;
}
