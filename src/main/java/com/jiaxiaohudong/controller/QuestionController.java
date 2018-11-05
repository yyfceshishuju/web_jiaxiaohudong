package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonQuestion;
import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.service.QuestionService;
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
    @RequestMapping(value = "/addquestion", method = RequestMethod.POST)
    public R addQuestion(@RequestParam(value = "question") CommonQuestion question ) throws RuntimeException {
        question.setAddtime(new Date().getTime());
        int i = questionService.add(question);
        if (i == 0){
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
        question.setId(Integer.parseInt(sid));
        question.setAddtime(date.getTime());
        List<CommonQuestion> commonQuestions = questionService.searchByCommonQuestion(question);
        if (commonQuestions !=null){
            map.put("question", commonQuestions);
            return  R.ok(map);
        }else {
            return  R.error("该学生本周没有错题");
        }

    }







}
