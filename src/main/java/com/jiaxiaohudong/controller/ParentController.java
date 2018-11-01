package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.dao.CommonStudentMapper;
import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.entity.CommonUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ParentController {

    @Autowired
    private CommonStudentMapper commonStudentMapper;

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public String bingStu(HttpServletRequest request, Model model, HttpSession session) {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        String studentId = request.getParameter("studentId");
        String phoneNum = request.getParameter("phoneNum");
        long phNum = user.getPhone();
        System.out.println(phoneNum + " " + phNum);
        if(Long.parseLong(phoneNum) == phNum){
            CommonStudent student = commonStudentMapper.selectByStudentIdAPhNum(studentId, phNum);
            if(student != null){
                student.setPid(user.getId());
                commonStudentMapper.updateByPrimaryKey(student);
                model.addAttribute("info", "success");
                return "bind";
            }
        }
        model.addAttribute("info", "failed");
        return "bind";
    }
}
