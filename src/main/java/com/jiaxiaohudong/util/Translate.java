package com.jiaxiaohudong.util;

import javax.servlet.http.HttpServletRequest;

public class Translate {
    public static String getType(int type){
        String[] types = {"管理员", "教师", "家长"};
        return types[type];
    }
    public static String getPath(HttpServletRequest request){
        return  request.getSession().getServletContext().getRealPath("/") + "../../src/main/webapp/upload";
    }
}
