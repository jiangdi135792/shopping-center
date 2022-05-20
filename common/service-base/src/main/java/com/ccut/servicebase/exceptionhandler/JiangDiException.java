package com.ccut.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//自定义异常类
@Data                //get和set构造方法
@AllArgsConstructor //生成有参注解
@NoArgsConstructor //生成无参构造方法
public class JiangDiException extends RuntimeException{
    private Integer code;//异常的状态码
    private String msg;//异常信息

}
