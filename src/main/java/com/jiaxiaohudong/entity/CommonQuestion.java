package com.jiaxiaohudong.entity;

public class CommonQuestion {
    private Integer id = 0;

    private Integer cid = 0;

    private String name = null;

    private String question = null;

    private String detail = null;

    private Integer uid = 0;

    private  Integer sid = 0;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    private Integer star = 0;

    private Integer answer = 0;

    private Long addtime = 0L;

    private Byte status = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public Long getAddtime() {
        return addtime;
    }

    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommonQuestion{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", question='" + question + '\'' +
                ", detail='" + detail + '\'' +
                ", uid=" + uid +
                ", sid=" + sid +
                ", star=" + star +
                ", answer=" + answer +
                ", addtime=" + addtime +
                ", status=" + status +
                '}';
    }
}