package com.ccut.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写的操作
        //1、设置写入文件夹的地址和excel的名称
       // String filename="C:\\Users\\姜迪\\Desktop\\project\\excel\\write.xls";
        //2、第啊用easy excel里面的方法实现写操作
        //write方法中两个参数，第一个文件路径名称，第二个参数实体类class
       // EasyExcel.write(filename,demoDate.class).sheet("学生列表").doWrite( getData() );

        //实现excel读操作
        String filename="C:\\Users\\姜迪\\Desktop\\project\\excel\\write.xls";
        EasyExcel.read(filename,demoDate.class,new ExcelListener()).sheet().doRead();
    }
    //创建方法返回list集合
    private static List<demoDate> getData() {
   List<demoDate> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            demoDate data = new demoDate();
            data.setSon(i);
            data.setSname( "lucy"+i );
            list.add( data );
        }
        return list;
    }
}
