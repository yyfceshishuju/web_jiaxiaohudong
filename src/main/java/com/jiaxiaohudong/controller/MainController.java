package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.Userinfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by lcf12307 on 2018/10/27.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String returnMainPage() {
        return "redirect:/index.do";
    }

    @RequestMapping(value="/login.do", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/register.do", method = RequestMethod.GET)
    public String register(String msg) {
        msg = "error";
        return "register";
    }

    @RequestMapping(value="/bind.do", method = RequestMethod.GET)
    public String bind() {
        return "bind";
    }

    @RequestMapping(value="/user.do", method = RequestMethod.GET)
    public String user() {
        return "user";
    }

    @RequestMapping(value="/class.do", method = RequestMethod.GET)
    public String clazz() {
        return "class";
    }
    @RequestMapping(value="/home.do", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value="/student/detail.do", method = RequestMethod.GET)
    public String detail() {
        return "student/detail";
    }

    @RequestMapping(value="/student/add.do", method = RequestMethod.GET)
    public String studentadd() {
        return "student/add";
    }

    @RequestMapping(value="/note.do", method = RequestMethod.GET)
    public String note() {
        return "note";
    }

    @RequestMapping(value="/question.do", method = RequestMethod.GET)
    public String question() {
        return "question";
    }

    @RequestMapping(value="/report.do", method = RequestMethod.GET)
    public String report() {
        return "report";
    }

    @RequestMapping(value="/report/detail.do", method = RequestMethod.GET)
    public String reportDetail() {
        return "report/detail";
    }

    @RequestMapping("/index.do")
    public String checkLogin(HttpSession session) {
        Userinfo user = (Userinfo) session.getAttribute("userinfo");
        if(user == null){
            return "redirect:/login.do";
        }  else{
            return "redirect:/home.do";
        }

    }

}
