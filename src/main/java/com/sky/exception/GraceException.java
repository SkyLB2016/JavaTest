package com.sky.exception;

import com.sky.utils.JSONResult;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class GraceException {

    public static void display(String msg){
        throw new MyCustomException(msg);

    }
}
