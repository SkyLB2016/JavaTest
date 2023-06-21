package com.sky.exception;

/**
 * 自定义异常的统一处理类，统一抛出异常
 */
public class GraceException {

    //抛出异常，通过拦截器，把错误信息返回给前台
    public static void display(String msg){
        throw new MyCustomException(msg);
    }
}
