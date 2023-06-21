package com.sky.service;

import com.sky.bean.Student;

import java.util.List;

public interface StudentService {

    /**
     * 新增数据
     *
     * @param stu
     */
    void insertStudent(Student stu);

    /**
     * 根据主键id查询
     *
     * @param id
     */
    Student queryById(String id);

    /**
     * 组合条件查询，模糊查询
     *
     * @param name
     * @param age
     * @return
     */
    List<Student> queryByCondition(String name, Integer age);

    /**
     * 分页查询
     *
     * @param name
     * @param age
     * @param page
     * @param pageSize
     * @return
     */
    List<Student> queryByCondition(String name, Integer age, Integer page, Integer pageSize);

    /**
     * 自定义语句查询,在 StudentMapperCustom.xml 文件中自定义的查询语句
     *
     * @param age
     * @param page
     * @param pageSize
     * @return
     */
    List<Student> getStudentByAge(Integer age, Integer page, Integer pageSize);

    /**
     * 自定义语句查询，在 StudentMapperCustom.xml 文件中自定义的查询语句
     *
     * @param score    自定义的语句
     * @param page
     * @param pageSize
     * @return
     */
    List<Student> getStudentByCustom(String score, Integer page, Integer pageSize);

    /**
     * 根据对象查询,精确查询
     *
     * @param stu
     * @return
     */
    List<Student> queryByObject(Student stu);

    /**
     * 根据主键来更新数据
     *
     * @param stu
     */
    void updateStudentByKey(Student stu);

    /**
     * 根据自定义的条件，更新数据
     *
     * @param stu
     */
    void updateStudent(Student stu);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteStudentById(String id);

    /**
     * 自定义删除条件
     * @param student
     */
    void deleteByExample(Student student);


    /**
     * 根据对象的参数条件，进行删除，等同于自定义条件删除，
     * @param student
     */
    void deleteByStudent(Student student);
}
