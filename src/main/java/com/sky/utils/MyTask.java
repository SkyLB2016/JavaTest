package com.sky.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration          //1.标记配置类，让springboot容器可以扫描到
@EnableScheduling       //2.开启定时任务
@Slf4j
public class MyTask {
    public int count = 0;

    //    @Scheduled(cron = "*/5 * * * * ?")
//    @Scheduled(cron = "0 */1 * * * ?")//添加一个任务，并且注明任务的运行方式
    @Scheduled(cron = "0 0 23 * * ?")
    public void publicMsg() {
        log.warn("开始执行定时任务,第" + (++count) + "次：" + LocalDateTime.now());
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
