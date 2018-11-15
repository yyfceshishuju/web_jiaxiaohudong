package com.jiaxiaohudong.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Translate {
    public static String getType(int type){
        String[] types = {"管理员", "教师", "家长"};
        return types[type];
    }
    public static String getPath(HttpServletRequest request){
//        return  request.getSession().getServletContext().getRealPath("/") + "../../src/main/webapp/upload";
        //线上上传地址
        return  request.getSession().getServletContext().getRealPath("/") + "../../webapps/upload";

    }

    public static String getGrade(String grade){
        Map<String, String> grades = new HashMap<String, String>();
        grades.put("1", "一年级");
        grades.put("2", "二年级");
        grades.put("3", "三年级");
        grades.put("4", "四年级");
        grades.put("5", "五年级");
        grades.put("6", "六年级");
        String res = grades.get(grade);
        if(res == null) return "";
        return res;
    }

    public static String getErrMsg(int errno){

        //todo 返回错误信息
        return "error";
    }
}
