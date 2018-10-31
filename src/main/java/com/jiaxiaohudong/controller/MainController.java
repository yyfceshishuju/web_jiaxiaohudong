package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.dao.CommonStudentMapper;
import com.jiaxiaohudong.entity.CommonStudent;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        return "bind";
    }

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public String bingStu(HttpServletRequest request){
        String studentId = request.getParameter("studentId");
        String phoneNum = request.getParameter("phoneNum");
        if(true){ //parents' phone?
            CommonStudent student = commonStudentMapper.selectByStudentId(studentId);
            student.setPid(2);
            commonStudentMapper.updateByPrimaryKey(student);
        }
        return "home";
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
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> homeGradSel(@RequestParam("grad") String grad){
        System.out.println(grad);
        Map<String, String> map = new HashMap<String, String>();
        //todo update grad who?
        String result = "ok";
        map.put("result", result);
        return map;
    }

    @RequestMapping(value="/student/detail.do", method = RequestMethod.GET)
    public String detail(Model model) {
        int stuId = 1;
        CommonStudent stu = commonStudentMapper.selectByPrimaryKey(stuId);
        model.addAttribute("stu", stu);
        return "student/detail";
    }

    @RequestMapping(value="/student/add.do", method = RequestMethod.GET)
    public String studentadd() {
        return "student/add";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public String addStu(HttpServletRequest request, CommonStudent student, MultipartFile pictureFile) throws Exception {
        //使用UUID给图片重命名，并去掉四个“-”
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        //获取文件的扩展名
        String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
        //设置图片上传路径
        String url = "E:\\projects\\upload";
        System.out.println(url);
        //以绝对路径保存重名命后的图片
        pictureFile.transferTo(new File(url+"/"+name + "." + ext));
        //把图片存储路径保存到数据库
        student.setIcon("upload/"+name+"."+ext);
        student.setName(request.getParameter("username"));
        student.setStudentid(request.getParameter("studentId"));
        System.out.println("sex: " + request.getParameter("radio1"));
        if(request.getParameter("radio1").equals("男")){
            student.setSex((byte)0);
        }
        else{
            student.setSex((byte)1);
        }
        student.setPhone(Long.parseLong(request.getParameter("phoneNum"), 10));
        String grad = request.getParameter("select2");
        System.out.println("grade: " + grad);
        if(grad.equals("1")){
            student.setGrad((byte)1);
        }
        else if(grad.equals("2")){
            student.setGrad((byte)2);
        }
        else if(grad.equals("3")){
            student.setGrad((byte)3);
        }
        else if(grad.equals("4")){
            student.setGrad((byte)4);
        }
        else if(grad.equals("5")){
            student.setGrad((byte)5);
        }
        else if(grad.equals("6")){
            student.setGrad((byte)6);
        }
        else{
            student.setGrad((byte)0);
        }
        student.setBirthday(19950420);
        student.setPid(1);
        student.setTid(1);
        student.setQuestion(0);
        student.setAnswer(0);
        student.setAddtime(new Long(new Date().getTime()).intValue());
        student.setStatus((byte)0);

        commonStudentMapper.insert(student);

        return "redirect: detail.do";
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
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        if(user == null){
            return "redirect:/login.do";
        }  else{
            return "redirect:/home.do";
        }

    }

}
