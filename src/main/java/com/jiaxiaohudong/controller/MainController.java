package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.dao.CommonStudentMapper;
import com.jiaxiaohudong.entity.CommonStudent;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by lcf12307 on 2018/10/27.
 */
@Controller
public class MainController {

    @Autowired
    private CommonStudentMapper commonStudentMapper;

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
        return "/bind";
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
    public String home(Model model, HttpSession session) {
        return "home";
    }

    @RequestMapping(value="/student/detail.do", method = RequestMethod.GET)
    public String detail(HttpSession session, Model model) {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        List<CommonStudent> stuList = commonStudentMapper.selectByPid(user.getId());
        model.addAttribute("stuList", stuList);
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

    @RequestMapping(value="/report/score.do", method = RequestMethod.GET)
    public String reportScore() {
        return "report/score";
    }

    @RequestMapping("/index.do")
    public String checkLogin(HttpSession session) {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        if(user == null){
            return "redirect:/login.do";
        }  else{
            return "redirect:/home.do";
        }

    }

}
