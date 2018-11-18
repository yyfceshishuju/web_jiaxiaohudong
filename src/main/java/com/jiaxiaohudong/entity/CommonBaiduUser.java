package com.jiaxiaohudong.entity;

public class CommonBaiduUser {
    private Integer id;
    private String name;
    private String app_id;
    private String api_key;
    private String secret_key;

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

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public CommonBaiduUser(){}

    public CommonBaiduUser(Integer id, String name, String app_id, String api_key, String secret_key) {
        this.id = id;
        this.name = name;
        this.app_id = app_id;
        this.api_key = api_key;
        this.secret_key = secret_key;
    }

    @Override
    public String toString() {
        return "CommonBaiduUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", app_id='" + app_id + '\'' +
                ", api_key='" + api_key + '\'' +
                ", secret_key='" + secret_key + '\'' +
                '}';
    }
}
