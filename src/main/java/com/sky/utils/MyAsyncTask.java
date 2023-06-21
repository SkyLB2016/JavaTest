package com.sky.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component          //1.标记配置类，让springboot容器可以扫描到
@EnableAsync       //2.开启异步任务
@Slf4j
public class MyAsyncTask {

//    @Scheduled(cron = "*/5 * * * * ?")
    @Async
    public void publicMsg(){
        try {
            Thread.sleep(5000);
            log.warn("异步任务执行完成："+ LocalDateTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Cron表达式范例：
    // */5 * * * * ?：每隔5秒执行一次
    // 0 */1 * * * ?：每隔1分钟执行一次
    // 0 0 23 * * ?：每天23点执行一次
    // 0 0 1 * * ?：每天凌晨1点执行一次：
    // 0 0 1 1 * ?：每月1号凌晨1点执行一次
    // 0 0 23 L * ?： 每月最后一天23点执行一次
    // 0 0 1 ? * L：每周星期天凌晨1点实行一次
    // 0 26,29,33 * * * ?： 在26分、29分、33分执行一次
    // 0 0 0,13,18,21 * * ?： 每天的0点、13点、18点、21点都执行一次 
}
