package com.sky.config;

import com.sky.entity.DogEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //说明当前类为配置类，让springboot容器可以扫描到
/**
 * @Bean
 * @Controller
 * @Service
 * @Repository
 * @component
 * 这些组件注解也都能使用，根据场景以及类的业务去使用和定义即可
 */
public class CommonConfig {

    @Bean
    public DogEntity dog() {
        DogEntity dog = new DogEntity();
        dog.setName("名字");
        dog.setGender(1);
        dog.setDescribe("自定义 entity 类：DogEntity，然后通过 CommonConfig 来创建对象，再通过@AutoWired 对接实体变量名");
        return dog;
    }
}
