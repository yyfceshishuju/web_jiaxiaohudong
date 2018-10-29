package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.baidu_service.Sample;
import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.entity.Userinfo;
import com.jiaxiaohudong.service.CommonUserService;
import com.jiaxiaohudong.service.UserinfoService;
import com.jiaxiaohudong.util.Encryption;
import com.jiaxiaohudong.util.R;
import com.jiaxiaohudong.util.SendMessage;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yyf on 2018/10/17.
 */
@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserinfoService userService;
    @Autowired
    private CommonUserService cuService;

    @RequestMapping(value="/login.do", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginValidate(HttpSession session, Model model, @ModelAttribute Userinfo user) {
        List<Userinfo> list = new ArrayList<Userinfo>();
        Userinfo record  = new Userinfo();
        record.setName(user.getName());
        list = userService.selectSelective(record);
        if (list.size() == 0) {
            model.addAttribute("status", 1);
        } else {
            record.setPw(Encryption.MD5(user.getPw()));
            list = userService.selectSelective(record);
            if (list.size() == 0) {
                model.addAttribute("status", 2);
            }
            record = list.get(0);
            session.setAttribute("userinfo", record);
            model.addAttribute("status", 0);
        }

        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        //session.removeAttribute("user");
        return "login";
    }

    @RequestMapping(value="/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, HttpSession session) {
        Userinfo user = (Userinfo) session.getAttribute("userinfo");
        if(user != null){
            model.addAttribute("user", user);
        }

        return "userInfo";
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
        String fileName = "";
        String orignName = "";
        long time = new Date().getTime() / 1000;
        if (uploaderInput == null || uploaderInput.isEmpty()) {
            orignName = "/img/logo.png";
        }else{
            // 获取文件名
            String path = "/home/cf/jxsq/web_for_jiaxiaoshequ/jxsq/web_jiaxiaohudong/src/main/webapp/upload";
            orignName = uploaderInput.getOriginalFilename();
            fileName = time + "." + orignName.substring(orignName.lastIndexOf(".") + 1);
            orignName = "/upload/" + fileName;
            try {
                File f2 = new File(path, fileName);
                uploaderInput.transferTo(f2);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
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
