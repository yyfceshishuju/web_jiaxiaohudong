package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.dao.CommonStudentMapper;
import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.entity.CommonUser;
import org.apache.commons.io.FilenameUtils;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class studentController {

    @Autowired
    private CommonStudentMapper commonStudentMapper;

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public void addStu(HttpServletRequest request, CommonStudent student, MultipartFile pictureFile,
                       HttpSession session, Model model) throws Exception {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        String grad = user.getGrade();
        System.out.println("grade: " + grad);
        if(grad == null){
            grad = "0";
        }
//        System.out.println(user);
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
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(d);
        student.setAddtime(Integer.parseInt(dateNowStr));
        student.setStatus((byte)0);

        commonStudentMapper.insert(student);
        model.addAttribute("info", "success");
    }

    @RequestMapping(value = "/teacher/grade", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> homeGradSel(@RequestParam("grad") String grad, HttpSession session){
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
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
        Map<String, String> map = new HashMap<String, String>();
        String result = "ok";
        map.put("result", result);
        return map;
    }

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public String bingStu(HttpServletRequest request, Model model, HttpSession session) {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        String studentId = request.getParameter("studentId");
        String phoneNum = request.getParameter("phoneNum");
        long phNum = user.getPhone();
        System.out.println(phoneNum + " " + phNum);
        if(Long.parseLong(phoneNum) == phNum){
            CommonStudent student = commonStudentMapper.selectByStudentIdAPhNum(studentId, phNum);
            if(student != null){
                student.setPid(user.getId());
                commonStudentMapper.updateByPrimaryKey(student);
                model.addAttribute("info", "success");
                return "bind";
            }
        }
        model.addAttribute("info", "failed");
        return "bind";
    }
}
