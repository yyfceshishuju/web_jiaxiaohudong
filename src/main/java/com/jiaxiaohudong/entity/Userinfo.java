package com.jiaxiaohudong.entity;

import java.util.Date;

public class Userinfo {
    private Integer id;

    private Boolean sex;

    private String name;


    private String phone;

    private String icon;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String pw;

    public String getPhone() {
        return phone;
    }

    public String getIcon() {
        return icon;
    }

    public String getOldpass() {
        return oldpass;
    }

    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    public String getNewpass1() {
        return newpass1;
    }

    public void setNewpass1(String newpass1) {
        this.newpass1 = newpass1;
    }

    public String getNewpass2() {
        return newpass2;
    }

    public void setNewpass2(String newpass2) {
        this.newpass2 = newpass2;
    }

    private String oldpass;
    private String newpass1;
    private String newpass2;

    private Date createtime;

    public Userinfo() {
    }

    public Userinfo(String name, String phone, String icon, String pw, Date createtime) {

        this.name = name;
        this.phone = phone;
        this.icon = icon;
        this.pw = pw;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}