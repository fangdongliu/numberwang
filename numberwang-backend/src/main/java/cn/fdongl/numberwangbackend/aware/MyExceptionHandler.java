package cn.fdongl.numberwangbackend.aware;

import cn.fdongl.numberwangentity.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception ex){
        return Result.fail(ex.getMessage());
    }

}
