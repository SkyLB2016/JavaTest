package com.sky.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AopLogSout {
    /**
     * AOP的通知类型：
     * 1.前置通知
     * 2.后置通知
     * 3.环绕通知
     * 4.异常通知
     * 5.最终通知
     * <p>
     * 前两个点代表：当前包和他的子包；
     * 后边括号里的两个点，代表每个方法接收的参数
     * 第一个 星号 * 代表 监听方法的返回，这里 * 代表不限制。
     * 第二个 星号 * 代表类名，
     * 第三个 星号 * 代表方法名
     */
    @Around("execution(* com.sky.service.impl..*.*(..))")
    public Object printLogTimesOfService(ProceedingJoinPoint point) throws Throwable {
        log.info("正在执行{}.{}", point.getTarget().getClass(), point.getSignature().getName());
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        if (time>3000){
            log.error("当前执行耗时：{}",time);
        }else if (time>2000){
            log.error("当前执行耗时：{}",time);
        }else{
            log.info("当前执行耗时：{}",time);
        }
        return null;
    }
}
