package com.jiaxiaohudong.util;

public class Translate {
    public static String getType(int type){
        String[] types = {"管理员", "教师", "家长"};
        return types[type];
    }
}
