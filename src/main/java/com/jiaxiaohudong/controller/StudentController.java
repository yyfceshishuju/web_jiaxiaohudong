package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.service.StudentService;
import com.jiaxiaohudong.util.R;
import com.jiaxiaohudong.util.Translate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public R getStudents(HttpSession session ) throws RuntimeException {

        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        List<CommonStudent> students = studentService.getStudents(user.getId());
        return handles(students);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public R searchStudents(HttpSession session, @RequestParam(value = "name") String name ) throws RuntimeException {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");

        CommonStudent commonStudent = new CommonStudent();
        commonStudent.setTid(user.getId());
        commonStudent.setName(name);
        List<CommonStudent> students = studentService.searchStudents(commonStudent);
        System.out.println(students);
        return handles(students);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addStu(HttpServletRequest request, MultipartFile pictureFile,
                         HttpSession session, Model model) {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        CommonStudent student = new CommonStudent();

        String grad = user.getGrade();
        if(grad == null){
            grad = "0";
        }
        String fileName = "";
        String orignName = "";
        String path = Translate.getPath(request);
        long time = new Date().getTime() / 1000;
        if (pictureFile == null || pictureFile.isEmpty()) {
            orignName = "/img/logo.png";
        }else{
            // 获取文件名
            orignName = pictureFile.getOriginalFilename();
            fileName = time + "." + orignName.substring(orignName.lastIndexOf(".") + 1);
            System.out.println(fileName);
            orignName = "/upload/" + fileName;
        }

        student.setName(request.getParameter("username"));
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int tid = user.getId();
        int radnum = (int)(Math.random()*899) + 100;
        String strtid = Integer.toString(tid);
        while (strtid.length() < 4){
            strtid = "0" + strtid;
        }
        strtid = Integer.toString(year) + strtid + Integer.toString(radnum);
        System.out.println(strtid);
        student.setStudentid(strtid);
        System.out.println("sex: " + request.getParameter("radio1"));
        if(request.getParameter("radio1").equals("男")){
            student.setSex((byte)0);
        }
        else{
            student.setSex((byte)1);
        }
        student.setPhone(Long.parseLong(request.getParameter("phoneNum"), 10));
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
        student.setTid(tid);
        Long addTime = time;
        student.setAddtime(addTime.intValue());

        List<CommonStudent> cStus = studentService.searchStudentsByNameAPhone(student);

        if(cStus == null || cStus.isEmpty()){
            //把图片存储路径保存到数据库
            student.setIcon(orignName);
            try {
                File f2 = new File(path, fileName);
                pictureFile.transferTo(f2);
            }catch (Exception e){
                e.printStackTrace();
            }
            studentService.insert(student);
            model.addAttribute("info", "success");
        }
        else{
            model.addAttribute("info", "exist");
        }

    }

    private R handles(List<CommonStudent> students){
        Map<String, Object> result = new HashMap<String, Object>();
        if (students !=null){
            result.put("data", students);
            result.put("code", 0);
            return R.ok(result);
        }else{
            return R.error("没有查询结果");
        }
    }
}