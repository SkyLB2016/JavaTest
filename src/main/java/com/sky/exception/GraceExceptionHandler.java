package com.sky.exception;

import com.sky.utils.JSONResult;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常拦截处理
 * 可以针对系统异常，自定义异常，处理捕获，返回指定的类型。
 */
@ControllerAdvice //注册异常监听类
public class GraceExceptionHandler {

    //定义需要拦截的错误信息
    @ExceptionHandler({FileSizeLimitExceededException.class})//需要拦截的异常
    @ResponseBody
    public JSONResult returnMaxFileSizeLimit(FileSizeLimitExceededException e) {
        return JSONResult.errorMsg("文件大小不能超过500KB");
    }

    //自定义需要拦截的错误信息
    @ExceptionHandler({MyCustomException.class})
    @ResponseBody
    public JSONResult returnMyCustomException(MyCustomException e) {
        return JSONResult.errorMsg(e.getMessage());
    }

    //自定义需要拦截的错误信息
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public JSONResult returnMyCustomException(MethodArgumentNotValidException e) {
        return JSONResult.errorMsg(e.getMessage());
    }

}