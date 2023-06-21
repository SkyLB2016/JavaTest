package com.sky.entity;

import lombok.*;

/**
 * lombok bean文件类注解
 * 1）@Data 生成参数的get与set
 * 2）@ToString  生成tostring方法
 * 3）@NoArgsConstructor  生成默认构造函数
 * 4）@AllArgsConstructor 生成全参构造函数
 * 5）@Slf4j 日志输出
 */
@Data
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
public class DogEntity {
    private String name;
    private int gender;
    private String describe;

    public DogEntity(String name, int gender) {
        this.name = name;
        this.gender = gender;
    }
}
