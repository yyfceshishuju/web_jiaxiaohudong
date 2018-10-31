package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.service.StudentService;
import com.jiaxiaohudong.util.JsonUtil;
import com.jiaxiaohudong.util.R;
import com.jiaxiaohudong.util.Translate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public R getStudents(HttpSession session ) throws RuntimeException {

        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        List<CommonStudent> students = studentService.getStudents(user.getId());
        if (students !=null){
            final String result = JsonUtil.toJson(students);
            return R.ok(result);
        }else{
            return R.error("没有查询结果");
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public R searchStudents(HttpSession session, @RequestParam(value = "name") String name ) throws RuntimeException {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");

        CommonStudent commonStudent = new CommonStudent();
        commonStudent.setTid(user.getId());
        commonStudent.setName(name);
        List<CommonStudent> students = studentService.searchStudents(commonStudent);
        if (students !=null){
            final String result = JsonUtil.toJson(students);
            return R.ok(result);
        }else{
            return R.error("没有查询结果");
        }

    }


}
