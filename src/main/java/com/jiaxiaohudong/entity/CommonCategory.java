package com.jiaxiaohudong.entity;

public class CommonCategory {
    private Integer id;

    private String name;

    private Byte type;

    public CommonCategory(String name, Byte type) {
        this.name = name;
        this.type = type;
    }

    public CommonCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CommonCategory(){

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}