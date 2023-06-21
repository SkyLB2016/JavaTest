package com.sky.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component //被容器扫描，并放入容器中
@ConfigurationProperties(prefix = "config")//配置的前缀，必须一致
@PropertySource(value= "classpath:MyConfig.properties",encoding = "utf-8")//关联配置文件
public class MyConfig {
    private String name;
    private Integer age;
    private String gender;
    private String describe="properties 文件配置的属性类";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
