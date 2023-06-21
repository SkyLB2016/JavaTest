package com.sky.controller;

import com.sky.bean.Student;
import com.sky.param.StudentParam;
import com.sky.service.StudentService;
import com.sky.utils.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("student") //本页面请求的统一前缀。
@Slf4j
public class StudentController {

    //依赖注入
    @Autowired
    private StudentService studentService;

    /**
     * 通过 map 获取参数
     *
     * @param map 上传的json数据，转成map
     * @return
     */
    @PostMapping("add")
    public JSONResult addStudent(@RequestBody Map<String, Object> map) {
        String id = UUID.randomUUID().toString();
        Student student = new Student();
        student.setId(id);
        student.setName((String) map.get("name"));
        student.setAge((Integer) map.get("age"));
        student.setGender((Integer) map.get("gender"));
        student.setAddress((String) map.get("address"));
        student.setScore((String) map.get("score"));
        studentService.insertStudent(student);
        return JSONResult.ok("以 @RequestBody 形式，通过 map 格式接收数据");
    }

    /**
     * 通过对象获取参数
     *
     * @param param  上传的json数据，转成对象
     *               注解 @Valid ，是开启 hibernate 校验
     * @param result 注解校验的错误信息集合，不写这个参数的话，会默认抛出 MethodArgumentNotValidException 错误，可以在统一异常信息中拦截处理
     */
    @PostMapping("create")
    public JSONResult createStudent(@Valid @RequestBody StudentParam param, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> map = getErrors(result);
            return JSONResult.errorMap(map);
        }
        String userid = UUID.randomUUID().toString();
        Student stu = new Student();
        BeanUtils.copyProperties(param, stu);
        stu.setId(userid);//为学生ID赋值了
        studentService.insertStudent(stu);
        return JSONResult.ok("以 @RequestBody 形式，通过 param 类的形式接收数据；注解 @Valid ，是开启 hibernate 校验；result 注解校验的错误信息集合，不写这个参数的话，会默认抛出 MethodArgumentNotValidException 错误，可以在统一异常信息中拦截处理");
    }

    /**
     * 通过 @PathVariable 注解获取参数，需要把参数写在？之前
     */
    @GetMapping("get/{id}")
    public JSONResult getPathStudent(@PathVariable("id") String userid) {
        Student stu = studentService.queryById(userid);
        return JSONResult.ok(stu);
    }

    /**
     * 通过 @RequestParam 获取参数，需要把参数写在？之后，同时@RequestParam 可以省略
     */
    @GetMapping("get")
//    public JSONResult getparamStudent(@RequestParam("id") String id) {//RequestParam可省略
    public JSONResult getParamStudent(String id) {
        Student stu = studentService.queryById(id);
        return JSONResult.ok(stu);
    }

    /**
     * 定制请求条件
     *
     * @param name
     * @param age
     * @return
     */
    @GetMapping("queryByCondition")
    public JSONResult queryByStudent(String name, Integer age) {
        List<Student> list = studentService.queryByCondition(name, age, 1, 10);
        return JSONResult.ok(list);
    }

    @GetMapping("getStudentByAge")
    public JSONResult getStudentByAge(Integer age) {
        List<Student> list = studentService.getStudentByAge(age, 1, 5);
        return JSONResult.ok(list);
    }

    @GetMapping("/getStudentByCustom")
    public JSONResult getStudentByCustom(String score) {
        List<Student> list = studentService.getStudentByCustom(score, 1, 5);
        return JSONResult.ok(list);
    }


    @GetMapping("queryByObject")
    public JSONResult queryByObjectStudent(@RequestBody StudentParam param) {
        Student student = new Student();
        BeanUtils.copyProperties(param, student);
        List<Student> list = studentService.queryByObject(student);
        return JSONResult.ok(list);
    }

    @PutMapping("updateStudentByKey")
    public JSONResult updateStudentByKey(@Valid @RequestBody StudentParam param,
                                         BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> map = getErrors(result);
            return JSONResult.errorMap(map);
        }
        Student stu = new Student();
        BeanUtils.copyProperties(param, stu);
        studentService.updateStudentByKey(stu);
        return JSONResult.ok();
    }

    @PutMapping("update")
    public JSONResult updateStudent(@Valid @RequestBody StudentParam param,
                                    BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> map = getErrors(result);
            return JSONResult.errorMap(map);
        }
        Student stu = new Student();
        BeanUtils.copyProperties(param, stu);
        studentService.updateStudent(stu);
        return JSONResult.ok();
    }

    @DeleteMapping("deleteStudentById")
    public JSONResult deleteStudentById(@RequestParam String id) {
        studentService.deleteStudentById(id);
        return JSONResult.ok();
    }

    @DeleteMapping("deleteByExample")
    public JSONResult deleteByExample(@Valid @RequestBody StudentParam param,
                                      BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> map = getErrors(result);
            return JSONResult.errorMap(map);
        }
        Student stu = new Student();
        BeanUtils.copyProperties(param, stu);
        studentService.deleteByExample(stu);
        return JSONResult.ok();
    }

    @DeleteMapping("delete")
    public JSONResult deleteStudent(@RequestBody StudentParam param) {
        Student stu = new Student();
        BeanUtils.copyProperties(param, stu);
        studentService.deleteByStudent(stu);
        return JSONResult.ok();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("testTrans")
    public JSONResult testTrans(@Valid @RequestBody StudentParam param, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> map = getErrors(result);
            return JSONResult.errorMap(map);
        }
        String userid = UUID.randomUUID().toString();
        Student stu = new Student();
        BeanUtils.copyProperties(param, stu);
        stu.setId(userid);//为学生ID赋值了
        studentService.insertStudent(stu);
        int a = 9 / 0;
        //错误信息不能try catch 拦截，拦截后就不回滚了了。
//        try {
//            int a = 9 / 0;
//        } catch (ArithmeticException e) {
//            return JSONResult.errorMsg("执行失败，事务注解：把一段代码同合成一个整体，中途发生错误，这段代码已经执行过的操作，会被回滚。");
//        }
        return JSONResult.ok(studentService.queryByObject(stu));
    }

    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> errors = result.getFieldErrors();
        for (FieldError error : errors) {
            String field = error.getField();//错误的字段名
            String msg = error.getDefaultMessage();//错误信息。
            map.put(field, msg);
        }
        return map;
    }
}
