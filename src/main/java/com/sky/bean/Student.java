package com.sky.bean;

import javax.persistence.Column;
import javax.persistence.Id;

public class Student {
    @Id
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别，0男，1女
     */
    private Integer gender;

    /**
     * 地址
     */
    private String address;

    /**
     * 分数
     */
    private String score;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 班级
     */
    @Column(name = "class_room")
    private Integer classRoom;

    /**
     * 技能，最多5个，（逗号分割？待定）
     */
    private String skill;

    /**
     * 邮箱
     */
    private String email;

    @Column(name = "english_name")
    private String englishName;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取性别，0男，1女
     *
     * @return gender - 性别，0男，1女
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别，0男，1女
     *
     * @param gender 性别，0男，1女
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取分数
     *
     * @return score - 分数
     */
    public String getScore() {
        return score;
    }

    /**
     * 设置分数
     *
     * @param score 分数
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * 获取年级
     *
     * @return grade - 年级
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置年级
     *
     * @param grade 年级
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取班级
     *
     * @return class_room - 班级
     */
    public Integer getClassRoom() {
        return classRoom;
    }

    /**
     * 设置班级
     *
     * @param classRoom 班级
     */
    public void setClassRoom(Integer classRoom) {
        this.classRoom = classRoom;
    }

    /**
     * 获取技能，最多5个，（逗号分割？待定）
     *
     * @return skill - 技能，最多5个，（逗号分割？待定）
     */
    public String getSkill() {
        return skill;
    }

    /**
     * 设置技能，最多5个，（逗号分割？待定）
     *
     * @param skill 技能，最多5个，（逗号分割？待定）
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return english_name
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * @param englishName
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
}