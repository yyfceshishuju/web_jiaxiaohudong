package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.entity.Userinfo;
import com.jiaxiaohudong.service.CommonUserService;
import com.jiaxiaohudong.util.R;
import com.jiaxiaohudong.util.Translate;
import com.jiaxiaohudong.util.Upload;
import org.apache.http.HttpRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

/**
 * Created by yyf on 2018/10/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CommonUserService cuService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public R loginValidate(HttpSession session, Model model, long phone, String password) {
        CommonUser user =  cuService.selectByPhone(phone);
        if ( null == user){
            return R.error(-1, "该用户不存在");
        }
        if (! user.getPassword().equals(password)){
            return R.error(-2, "密码输入错误");
        }
        session.setAttribute("userinfo", user);

        return R.ok("登录成功");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        //session.removeAttribute("user");
        return "login";
    }

    @RequestMapping(value="/info", method = RequestMethod.GET)
    @ResponseBody
    public R userInfo(HttpSession session) {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        if(user != null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", user.getName());
            map.put("icon", user.getIcon());
            map.put("phone", user.getPhone());
            map.put("type", Translate.getType(user.getType()));
            map.put("grade", Translate.getGrade(user.getGrade()));
            return R.ok(map);
        }

        return R.error(-4, "用户未登录");
    }


    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public R Update(HttpSession session, int key, String oldpass, String newpass1, String newpass2, @RequestParam(value="uploaderInput",required = false) MultipartFile uploaderInput,
                    HttpServletRequest request) {
        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        if(user != null){
            switch (key){
                case 1:
                    long time = new Date().getTime() / 1000;
                    String orignName = Upload.upload(time, uploaderInput, request);
                    String icon = orignName;
                    user.setIcon(icon);
                    break;
                case 2:

                    if (!newpass1.equals(newpass2)){
                        return R.error(-2, "两次密码不一致");
                    }
                    if (!user.getPassword().equals(oldpass)){
                        return R.error(-3, "旧密码输入有误");
                    }
                    user.setPassword(newpass1);
                    break;
            }
            R result = cuService.update(user);
            session.setAttribute("userinfo", user);
            return result;
        }

        return R.error(-4, "用户未登录");
    }


    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public R addUser(@RequestParam(value="uploaderInput",required = false) MultipartFile uploaderInput, String name, long phone,String code, String password,
                    HttpServletRequest request) {

        CommonUser user = cuService.selectByPhone(phone);
        if (user != null){
            System.out.println(user);
            return  R.error(-1, "该用户已经注册");
        }
        JSONObject json = null;
        long time = new Date().getTime() / 1000;
        String orignName = Upload.upload(time, uploaderInput, request);
        String icon = orignName;
        Long addtime = time;
        Byte status = 1;
        Byte type = 2;


        if (! code.equals(request.getSession().getAttribute("code"))){

            return  R.error(-2,"验证码输入有误");
        }

        CommonUser cu = new CommonUser(name, icon, phone, password, addtime, status, type);
        R result = cuService.insert(cu);
        return R.ok("注册成功");
    }


}
