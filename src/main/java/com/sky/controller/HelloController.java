package com.sky.controller;

import com.sky.bean.Dog;
import com.sky.bean.Person;
import com.sky.bean.Student;
import com.sky.utils.JSONResult;
import com.sky.utils.MyAsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController//相当于 @Controller 与 @ResponseBody 的结合
@Slf4j //日志输出注解。
public class HelloController {

    @Autowired
    private Student stud;

    @GetMapping("getStu")
    public Object getStu() {
        return stud;
    }


    @Autowired
    private Person person;

    @Autowired
    private MyAsyncTask task;

//    @RequestMapping("hello")
//    @ResponseBody
//    public String hello() {
//        return "hello world 真尼玛,改动后需要重启服务";
//    }

    @GetMapping("hello")
//    @PostMapping("custom")
//    @PutMapping("custom")
//    @DeleteMapping("custom")
    public String hello() {
        return "hello world 真尼玛,使用工具 devtools ，改动后配合 build project 使用 cmd +f9";
    }

    //    @Autowired
//    private MyConfig myConfig;
    @GetMapping("getMyConfig")
    public Object getMyConfig() {
        task.publicMsg();
        log.warn("已跳过异步任务");
//        return myConfig;
        return person;
    }


    @Value("${self.custom.config.sdkSecrect}")
    private String sdkSecrect;
    @Value("${self.custom.config.host}")
    private String host;
    @Value("${self.custom.config.port}")
    private String port;
//    private String xyz;

    @GetMapping("getYmlConfig")
    public Object getYmlConfig() {
        return sdkSecrect + ";\t" + host + ":" + port;
    }

    @GetMapping("getDog")
    public Object getDog() {
        Dog dog = new Dog();
        dog.setName("大黄");
        dog.setSex("雄性");
        log.debug(dog.toString());
        log.info(dog.toString());
        log.warn(dog.toString());
        log.error(dog.toString());
        return dog;
    }

    //    @SneakyThrows
    @PostMapping("submit")
    public JSONResult submit(MultipartFile filename) throws Exception {
        log.info("上传文件的参数==" + filename.getName());
        log.info("文件本身的名字==" + filename.getOriginalFilename());
        filename.transferTo(new File("/Users/sky/Documents/Java/" + filename.getOriginalFilename()));
        return JSONResult.ok();
    }

    //    @SneakyThrows
    @PostMapping("submit1")
//    public String submit(MultipartFile file) throws Exception{
    public JSONResult submit1(@RequestBody Map<String, Object> map) throws Exception {
        log.info("map==" + map.toString());
        MultipartFile file = (MultipartFile) map.get("file");
        file.transferTo(new File("/Users/sky/Documents/Java/1" + file.getOriginalFilename()));
//        file.transferTo(new File("/Users/sky/Documents/Python/" + file.getOriginalFilename()));
        return JSONResult.ok();
//        return "上传成功";
    }
}
