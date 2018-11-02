package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.dao.CommonCategoryMapper;
import com.jiaxiaohudong.dao.CommonReportMapper;
import com.jiaxiaohudong.entity.CommonCategory;
import com.jiaxiaohudong.entity.CommonReport;
import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.service.CommonUserService;
import com.jiaxiaohudong.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private CommonUserService cuService;
    @Autowired
    private CommonCategoryMapper ccMap;
    @Autowired
    private CommonReportMapper crMap;

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
        cuService.update(user);
        Map<String, String> map = new HashMap<String, String>();
        String result = "修改成功";
        map.put("result", result);
        return map;
    }

    @RequestMapping(value = "/scoreCategory", method = RequestMethod.GET)
    @ResponseBody
    public R getScoreCategory(){
        Map<String, Object> result = new HashMap<String, Object>();
        CommonCategory category = ccMap.selectByType((byte)2);
        result.put("code", 0);
        result.put("data", category);
        return R.ok(result);
    }

    @RequestMapping(value = "/addScore", method = RequestMethod.POST)
    @ResponseBody
    public R addScore(HttpServletRequest request, HttpSession session){
        CommonReport commonReport = new CommonReport();
        Map<String, Object> result = new HashMap<String, Object>();
        CommonUser commonUser = (CommonUser) session.getAttribute("userinfo");
        String score = request.getParameter("category");
        System.out.println(score);
        String judge = request.getParameter("assess");
        System.out.println(judge);
        int stuId = Integer.parseInt(request.getParameter("student"));
        System.out.println(stuId);

        Long addtime = new Date().getTime() / 1000;
        commonReport.setUid(commonUser.getId());
        commonReport.setAddtime(addtime.intValue());
        commonReport.setScore(score);
        commonReport.setJudge(judge);
        commonReport.setStuid(stuId);

        crMap.insert(commonReport);
        result.put("code", 0);
        result.put("msg", "提交成功");
        return R.ok(result);
    }
}
