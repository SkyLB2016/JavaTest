package com.sky.controller;

import com.sky.entity.DogEntity;
import com.sky.utils.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("dog")//默认前缀 /dog/
@Slf4j//日志输出
public class DogController {

    //    localhost/dog/get
//    http://localhost:8888/dog/get/234?id=99&id2=887&name2=name2&name=大黄
    @GetMapping("get/{dogId}")
    public Object getDog(@PathVariable("dogId") String dogId,
                         //参数名和value一样，@RequestParam 可以省略
                         @RequestParam int id,
                         int id2,
                         @RequestParam String name,
                         @RequestParam("name2") String name1
    ) {

        log.info("dogId==" + dogId);
        log.info("id==" + id);
        log.info("id2==" + id2);
        log.info("name==" + name);
        log.info("name1==" + name1);
        return "查询";
    }

    @PostMapping("create")
    public JSONResult createDog(@RequestBody Map<String, Object> map,
                                @RequestHeader("token") String token,
                                @CookieValue("clientId") String clientId,
                                HttpServletRequest request) {
        log.info("token==" + token);
        log.info("clientId==" + clientId);
        log.info("map==" + map.toString());

        String headerToken = request.getHeader("token");
        log.info("headerToken==" + headerToken);

        return JSONResult.ok(new DogEntity("大黄", 1));
    }

    @PutMapping("update")
    public Object updagteDog() {
        return "修改";
    }

    @DeleteMapping("delete")
    public Object deleteDog() {
        return "删除";
    }
}
