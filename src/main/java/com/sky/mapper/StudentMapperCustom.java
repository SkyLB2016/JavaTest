package com.sky.mapper;

import com.sky.bean.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapperCustom {

    List<Student> getStudentByAge(Integer age);

    List<Student> getStudentByScore(String score);
}