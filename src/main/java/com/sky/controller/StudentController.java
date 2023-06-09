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
        String userid = UUID.randomUUID().toString();
        Student student = new Student();
        student.setUserid(userid);
        student.setName((String) map.get("name"));
        student.setAge((Integer) map.get("age"));
        student.setScore((String) map.get("score"));
        studentService.insertStudent(student);
        return JSONResult.ok();
    }

    /**
     * 通过对象获取参数
     *
     * @param param 上传的json数据，转成对象
     * @return
     */
    @PostMapping("create")
    public JSONResult createStudent(@RequestBody StudentParam param) {
        String userid = UUID.randomUUID().toString();
        Student stu = new Student();
        BeanUtils.copyProperties(param, stu);
        log.warn(stu.getUserid());
        stu.setUserid(userid);
        studentService.insertStudent(stu);
        return JSONResult.ok();
    }

    /**
     * 把参数写在？之前，请求数据
     *
     * @param userid
     * @return
     */
    @GetMapping("getPath/{userid}")
    public JSONResult getPathStudent(@PathVariable("userid") String userid) {
        Student stu = studentService.queryById(userid);
        return JSONResult.ok(stu);
    }

    /**
     * 把参数写在？之后，请求数据
     *
     * @param userid
     * @return
     */
    @GetMapping("getParam")
//    public JSONResult getparamStudent(@RequestParam("userid") String userid) {//requestParam可省略
    public JSONResult getParamStudent(String userid) {
        Student stu = studentService.queryById(userid);
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

    @DeleteMapping("delete")
    public JSONResult deleteStudent(@Valid @RequestBody StudentParam param,
                                    BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> map = getErrors(result);
            return JSONResult.errorMap(map);
        }
        Student stu = new Student();
        BeanUtils.copyProperties(param, stu);
        studentService.deleteByStudent(stu);
        return JSONResult.ok();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("testTrans")
    public JSONResult testTrans(@Valid @RequestBody StudentParam param) {

        Student stu = new Student();
        BeanUtils.copyProperties(param, stu);
        studentService.insertStudent(stu);

        int a = 9 / 0;
        return JSONResult.ok(studentService.queryByObject(stu));
    }

    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> errors = result.getFieldErrors();
        for (FieldError error : errors) {
            String field = error.getField();
            String msg = error.getDefaultMessage();
            map.put(field, msg);
        }
        return map;
    }
}
