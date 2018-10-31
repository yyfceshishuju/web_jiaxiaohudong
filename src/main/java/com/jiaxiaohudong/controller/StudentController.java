package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.service.StudentService;
import com.jiaxiaohudong.util.JsonUtil;
import com.jiaxiaohudong.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping(value = "/getstudents", method = RequestMethod.POST)
    public R getStudents(@RequestParam(value = "tid") Integer tid ) throws RuntimeException {

        List<CommonStudent> students = studentService.getStudents(tid);
        if (students !=null){
            final String result = JsonUtil.toJson(students);
            return R.ok(result);
        }else{
            return R.error("没有查询结果");
        }
    }

    @RequestMapping(value = "/searchstudents", method = RequestMethod.POST)
    public R searchStudents(@RequestParam(value = "tid") Integer tid , @RequestParam(value = "name") String name ) throws RuntimeException {
        CommonStudent commonStudent = new CommonStudent();
        commonStudent.setTid(tid);
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
