package com.sky.mapper;

import com.sky.bean.Student;
import com.sky.mappers.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends MyMapper<Student> {
}