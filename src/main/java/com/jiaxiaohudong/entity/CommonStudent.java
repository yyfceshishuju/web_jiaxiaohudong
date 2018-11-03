package com.jiaxiaohudong.entity;

public class CommonStudent {
    private Integer id;

    private String name;

    private String studentid;

    private String icon;

    private Long phone;

    private Integer birthday;

    private Byte sex;

    private Integer pid;

    private Integer tid;

    private Integer question;

    private Integer answer;

    private Integer addtime;

    private Byte status;

    private Byte grad;

    public CommonStudent(String name, Integer id, String icon) {
        this.name = name;
        this.id = id;
        this.icon = icon;
    }

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

    public CommonStudent() {
        this.name = "";
        this.studentid = "";
        this.icon = "";
        this.phone = 0L;
        this.birthday = 0;
        this.sex = 0;
        this.pid = 0;
        this.tid = 0;
        this.question = 0;
        this.answer = 0;
        this.addtime = 0;
        this.status = 0;
        this.grad = 0;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
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

    public Byte getGrad() {
        return grad;
    }

    public void setGrad(Byte grad) {
        this.grad = grad;
    }
}