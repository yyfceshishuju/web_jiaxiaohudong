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
import java.text.SimpleDateFormat;
import java.util.*;

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
        Map<String, Object> result = new HashMap<String, Object>();
        CommonUser commonUser = (CommonUser) session.getAttribute("userinfo");
        String score = request.getParameter("category");
//        System.out.println(score);
        String judge = request.getParameter("assess");
//        System.out.println(judge);
        int stuId = Integer.parseInt(request.getParameter("student"));
//        System.out.println(stuId);

        Calendar calendar2 = Calendar.getInstance();
        int day = calendar2.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == 0) day = 7;
        int year = calendar2.get(Calendar.YEAR);
        CommonReport commonReport = new CommonReport();
        long time = new Date().getTime() / 1000;
        Long timeL = time;
        int addTime = timeL.intValue();
        System.out.println(addTime);
        commonReport.setUid(commonUser.getId());
        commonReport.setAddtime(addTime);
        commonReport.setScore(score);
        commonReport.setJudge(judge);
        commonReport.setStuid(stuId);
        commonReport.setStage(year);

        List<CommonReport> commonReports = crMap.selectByUidAStuidAYear(commonUser.getId(), stuId, year);
        if(commonReports == null || commonReports.isEmpty()){//none before
            System.out.println("empty");
            crMap.insert(commonReport);
            result.put("code", 0);
            result.put("msg", "提交成功");
        }
        else{//already have one
            Collections.sort(commonReports,new Comparator<CommonReport>(){
                public int compare(CommonReport arg0, CommonReport arg1) {
                    return arg1.getStage().compareTo(arg0.getStage());
                }
            });//降序排
            CommonReport last = commonReports.get(0);
            long lastt = last.getAddtime();
            calendar2.setTimeInMillis(lastt*1000);
            int day1 = calendar2.get(Calendar.DAY_OF_WEEK) - 1, day2 = day,
            sec1 = (int)lastt, sec2 = addTime;
            if (day1 == 0) day1 = 7;
            int dayPlus = (sec2- sec1) / 60 / 60 / 24;
            System.out.println("dayPlus " + dayPlus);
            if ((dayPlus >= 7) || (dayPlus < 7 && day2 < day1)){
                System.out.println("just add, a new week");
                crMap.insert(commonReport);
                result.put("code", 0);
                result.put("msg", "提交成功");
            }
            else{
                //just update
                System.out.println("update");
                last.setJudge(judge);
                last.setScore(score);
                last.setStage(year);
                last.setAddtime(addTime);
                crMap.updateByPrimaryKeySelective(last);
                result.put("code", 0);
                result.put("msg", "提交成功");
            }
        }

        return R.ok(result);
    }

}
