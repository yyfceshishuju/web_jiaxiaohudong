package com.jiaxiaohudong.entity;

public class CommonClass {
    private Integer id;

    private Long schoolname;

    private String grade;

    private String classname;

    private Integer torsid;

    private Byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(Long schoolname) {
        this.schoolname = schoolname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getTorsid() {
        return torsid;
    }

    public void setTorsid(Integer torsid) {
        this.torsid = torsid;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}