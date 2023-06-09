package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.bean.Student;
import com.sky.mapper.StudentMapper;
import com.sky.mapper.StudentMapperCustom;
import com.sky.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentMapperCustom studentMapperCustom;

    @Override
    public void insertStudent(Student stu) {
        studentMapper.insert(stu);
    }

    @Override
    public Student queryById(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Student> queryByCondition(String name, Integer age) {
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        if (!name.isEmpty())
            criteria.andEqualTo("name", name);
        if (age != null)
            criteria.andEqualTo("age", age);
        return studentMapper.selectByExample(example);
    }

    @Override
    public List<Student> queryByCondition(String name, Integer age, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        if (!name.isEmpty())
            criteria.andEqualTo("name", name);
        if (age != null)
            criteria.andEqualTo("age", age);
        List<Student> list = studentMapper.selectByExample(example);
//        log.warn("集合长度" + list.size());
//        log.warn("集合==" + list.toString());
        PageInfo<?> pageInfo = new PageInfo<>(list);
        log.warn(pageInfo.toString());
        return list;
    }

    @Override
    public List<Student> getStudentByAge(Integer age, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return studentMapperCustom.getStudentByAge(age);
    }

    @Override
    public List<Student> getStudentByCustom(String score, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return studentMapperCustom.getStudentByCustom(score);
    }

    @Override
    public List<Student> queryByObject(Student stu) {
        return studentMapper.select(stu);
    }

    @Override
    public void updateStudent(Student stu) {
        //自定义更新条件
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid", stu.getUserid());
//        criteria.andEqualTo("age", stu.getAge());
//        studentMapper.updateByExample(stu, example);//更新全部，为null，也会覆盖更新
//        studentMapper.updateByExampleSelective(stu, example);//有选择的更新，即为null的数据不更新


        //跟据主键来更新数据
//        studentMapper.updateByPrimaryKey(stu);//更新全部，为null，也会覆盖更新
        studentMapper.updateByPrimaryKeySelective(stu);//有选择的更新，即为null的数据不更新
    }

    @Override
    public void deleteByStudent(Student stu) {
        //自定义删除条件
//        Example example = new Example(Student.class);
//        Example.Criteria criteria = example.createCriteria();
////        criteria.andEqualTo("age", stu.getAge());
//        criteria.andCondition("age>20");
//        studentMapper.deleteByExample(example);//自定义删除条件

        //根据对象中属性值匹配做条件删除
        studentMapper.delete(stu);//根据对象中属性值匹配做条件删除
//        studentMapper.deleteByPrimaryKey(stu.getUserid());//通过主键来删除
    }
}
