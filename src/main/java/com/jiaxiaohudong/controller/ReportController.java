package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonReport;
import com.jiaxiaohudong.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @RequestMapping("/getreport")
    public String getReport(HashMap<String, Object> map){
        List<CommonReport> commonReports = reportService.showALl();
        map.put("reports", commonReports);
        return "report";
    }
}
