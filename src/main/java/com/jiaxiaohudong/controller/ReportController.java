package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonReport;
import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.service.ReportService;
import com.jiaxiaohudong.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.jiaxiaohudong.entity.CommonUser;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private StudentService studentService;



    @RequestMapping("/getreport")
    public String getReport(HttpSession session, HashMap<String, Object> map){
        Date date = new Date();
        long time = date.getTime();
	CommonUser user = (CommonUser)session.getAttribute("userinfo");
        List<CommonStudent> students = studentService.getStudents(user.getId());
        List<CommonReport> commonReports = new ArrayList<CommonReport>();
        for (CommonStudent student:students) {
            Integer sid = student.getId();
            CommonReport commonReport = reportService.selectById(new Long(sid), time);
            commonReports.add(commonReport);
        }
        map.put("reports", commonReports);
        return "report";
    }
}
