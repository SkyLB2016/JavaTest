package com.sky.controller;

import com.sky.entity.DogEntity;
import com.sky.entity.MyConfig;
import com.sky.utils.JSONResult;
import com.sky.utils.MyAsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController//相当于 @Controller 与 @ResponseBody 的结合
@Slf4j //日志输出注解。
public class HelloController {

    //yml中自定义的属性字段，表达式同样适用于 yml 中
    @Value("${self.custom.config.sdkSecrect}")
    private String sdkSecrect;
    @Value("${self.custom.config.host}")
    private String host;
    @Value("${self.custom.config.port}")
    private String port;
//    private String xyz;

    //autowired 依赖注入
    @Autowired
    DogEntity dog;

    //autowired 依赖注入，properties配置的 MyConfig
    @Autowired
    private MyConfig myConfig;

    @Autowired
    private MyAsyncTask myAsyncTask;

    //@RestController//相当于 @Controller 与 @ResponseBody 的结合
//    @PostMapping("custom") @PutMapping("custom") @DeleteMapping("custom")
    @GetMapping("hello")
    public String hello() {
        return "hello world 真尼玛,使用工具 devtools ，改动后配合 build project 刷新，快捷键 cmd +f9";
    }

    //控制反转，把创建stu的权利放在了配置（config）层
    @GetMapping("getDogConfig")
    public Object getDogConfig() {
        return dog;
    }

    @GetMapping("getMyConfig")
    public Object getMyConfig() {
        myAsyncTask.publicMsg();
        log.warn("已跳过异步任务");
        return myConfig;
    }

    @GetMapping("getYmlConfig")
    public Object getYmlConfig() {
        return "这获取的是yml中配置的属性：" + sdkSecrect + ";\t" + host + ":" + port;
    }

    @GetMapping("getDog")
    public Object getDog() {
        DogEntity dog = new DogEntity();
        dog.setName("大黄");
        dog.setGender(1);
        log.debug(dog.toString());
        log.info(dog.toString());
        log.warn(dog.toString());
        log.error(dog.toString());
        return dog;
    }

    //    @SneakyThrows
    @PostMapping("uploadFile")
    public JSONResult submit(MultipartFile fileName) throws Exception {
        log.info("上传文件的参数==" + fileName.getName());
        log.info("文件本身的名字==" + fileName.getOriginalFilename());
        fileName.transferTo(new File("/Users/sky/Documents/Java/" + fileName.getOriginalFilename()));
        return JSONResult.ok("被校验的方法，上传的参数名为" + fileName.getName() + "；文件本身的名字为：" + fileName.getOriginalFilename() + "；文件的存储路径为：/Users/sky/Documents/Java/");
    }
}
