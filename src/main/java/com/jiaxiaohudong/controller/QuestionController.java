package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonCategory;
import com.jiaxiaohudong.entity.CommonQuestion;
import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.service.CategoryService;
import com.jiaxiaohudong.service.QuestionService;
import com.jiaxiaohudong.service.StudentService;
import com.jiaxiaohudong.util.JsonUtil;
import com.jiaxiaohudong.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public R addQuestion(CommonQuestion commonQuestion, HttpSession session) throws RuntimeException {
        System.out.println(commonQuestion);
        CommonUser userinfo = (CommonUser) session.getAttribute("userinfo");
        Integer uid = userinfo.getId();
        commonQuestion.setUid(uid);
        commonQuestion.setDetail(commonQuestion.getQuestion());
        commonQuestion.setAddtime(new Date().getTime());
        int i = questionService.add(commonQuestion);
        System.out.println(i);
        if (i != 0){
            return R.ok("ok");
        }else{
            return R.error("error");
        }
    }

    @RequestMapping("/getquestions")//提供学生id和当前查询时间的周一，格式为yyyy-MM-dd kk:mm:ss
    public R getQuestions(@RequestParam(value = "sid") String sid,@RequestParam(value = "time") String time, Map<String, Object> map){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date date = null;
        try{
            date = format.parse(time);
        }catch(Exception e){
            e.printStackTrace();
        }

        CommonQuestion question = new CommonQuestion();
        question.setSid(Integer.parseInt(sid));
        question.setAddtime(date.getTime());
        List<CommonQuestion> commonQuestions = questionService.searchByCommonQuestion(question);
        if (commonQuestions !=null){
            map.put("questions", commonQuestions);
            return  R.ok(map);
        }else {
            return  R.error("该学生本周没有错题");
        }

    }

    @RequestMapping("/getquestion")//提供错题id，返回错题详情
    public String getQuestion(@RequestParam(value = "id") String id, Map<String, Object> map){

        CommonQuestion commonQuestion = questionService.searchByPrimaryKey(Integer.parseInt(id));
        if (commonQuestion !=null){
            map.put("question", commonQuestion);
            return  "question";
        }else {
            return  "error";
        }

    }

    @RequestMapping("getquestion_five")//根据学生id获取5个学生，用于下拉递增
    public String getQuestion(String sid, @RequestParam(value = "thispage") String thisPage, @RequestParam(value = "pagesize") String pageSize, Map<String, Object> map){
        if(sid != null){
            CommonStudent commonStudent = studentService.searchStrudent(Integer.valueOf(sid));
            List<CommonQuestion> commonQuestions = questionService.searchByPage(Integer.parseInt(sid), Integer.parseInt(thisPage) * Integer.parseInt(pageSize), Integer.parseInt(pageSize));
            if (commonQuestions !=null){
                for (CommonQuestion c: commonQuestions){
                    c.setStudent(commonStudent);
                    Integer cid = c.getCid();
                    CommonCategory commonCategory = categoryService.searchById(cid);
                    c.setCommonCategory(commonCategory);
                    Long addtime = c.getAddtime();
                    Date date = new Date(addtime);
                    SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );

                    c.setUtilTime(sdf.format(date));
                }
                map.put("questions", commonQuestions);
                return  "note";
            }else {
                return  "error";
            }
        }else{
            return "error";
        }

    }






}
