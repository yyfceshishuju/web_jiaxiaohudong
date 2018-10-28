package com.jiaxiaohudong.entity;

public class CommonUser {
    private Integer id;

    private String name;

    private String icon;

    private String grade;

    private Long phone;

    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public CommonUser() {

    }

    public CommonUser(String name, String icon, Long phone, String openid, String password, Integer addtime, Byte status, Byte type) {

        this.name = name;
        this.icon = icon;
        this.phone = phone;
        this.openid = openid;
        this.password = password;
        this.addtime = addtime;
        this.status = status;
        this.type = type;
    }

    private String password;

    private Integer rid;

    private Integer addtime;

    private Byte status;

    private Byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}