package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @RequestMapping(value = "/grade", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> homeGradSel(@RequestParam("grad") String grad, HttpSession session){
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        System.out.println(user.getId() + " " + user.getAddtime());
        System.out.println(grad);
        if(grad.equals("一年级")){
            user.setGrade("1");
        }
        else if(grad.equals("二年级")){
            user.setGrade("2");
        }
        else  if(grad.equals("三年级")){
            user.setGrade("3");
        }
        else  if(grad.equals("四年级")){
            user.setGrade("4");
        }
        else  if(grad.equals("五年级")){
            user.setGrade("5");
        }
        else  if(grad.equals("六年级")){
            user.setGrade("6");
        }
        else{
            user.setGrade("0");
        }
        Map<String, String> map = new HashMap<String, String>();
        String result = "修改成功";
        map.put("result", result);
        return map;
    }
}
