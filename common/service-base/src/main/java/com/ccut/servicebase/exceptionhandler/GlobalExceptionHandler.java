package com.ccut.servicebase.exceptionhandler;





import com.ccut.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //执行什么样的异常匹配
    @ExceptionHandler(Exception.class)//所有
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message( "执行全局异常处理.." );
    }
//特定异常处理
    @ExceptionHandler(ArithmeticException.class)//所有
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message( "执行全局ArithmeticException异常处理.." );
    }
    //自定义异常 需要手动抛出
    @ExceptionHandler(JiangDiException.class)//所有
    @ResponseBody
    public R error(JiangDiException e){
        log.error( e.getMessage() );
        e.printStackTrace();
        return R.error().code( e.getCode() ).message( e.getMsg() );
    }

/*try {
        int a = 10/0;
    }catch(Exception e) {
  throw new JiangDiException(20001,"出现自定义异常");
    }*/
}
