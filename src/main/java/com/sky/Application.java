package com.sky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类,主程序入口
 * 需要放在根目录之下,会默认扫描根目录下和子目录下的 controller 和 service 以及 mappers 等相关组件，
 * 扫描完成之后，会放入到 spring/springboot 的容器中
 */
@SpringBootApplication // 启动注解
@MapperScan(basePackages = "com.sky.mapper")//映射迁移进来的 mapper 文件，需要在application中引入
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
