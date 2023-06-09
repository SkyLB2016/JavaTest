package com.sky.config;

import com.sky.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //1.标记配置类，让springboot容器可以扫描到
public class CommonConfig {

    @Bean
    public Student student() {
        Student stu = new Student();

        stu.setName("名字");
        stu.setAge(18);
        stu.setScore("222");
        return stu;
    }
}
