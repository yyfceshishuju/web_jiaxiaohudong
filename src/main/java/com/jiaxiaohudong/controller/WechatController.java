package com.jiaxiaohudong.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.service.CommonUserService;
import com.jiaxiaohudong.util.R;
import com.jiaxiaohudong.util.SendMessage;
import com.jiaxiaohudong.util.Wechat;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 *
 * Created by lcf12307 on 2018/10/28.
 */

@Controller
@RequestMapping("/mp")
public class WechatController {


    @Autowired
    private CommonUserService cuService;

    @RequestMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        try {
            String url = Wechat.getOfficialAccAuthUrl(null, request.getSession().getId());
            response.sendRedirect(url + "&param=1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/bind")
    public void bind(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        try {
            String url = Wechat.getOfficialAccAuthUrl(null, request.getSession().getId());
            response.sendRedirect(url + "&param=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/callBackLogin")
    public String callBackLogin(HttpSession session, int param, HttpServletRequest request) {
        System.out.println("callBackLogin....");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        System.out.println("code=" + code);
        System.out.println("state=" + state);


        String url = Wechat.getTokenUrl(code);

        JSONObject jsonObject = Wechat.httpGet(url);
        String at = jsonObject.getString("access_token");//获取微信开放平台票据号
        String openId = jsonObject.getString("openid");
        if (at == null || openId == null){
            return "home";
        }
        url=Wechat.getUserInfoUrl(at, openId);
        jsonObject = Wechat.httpGet(url);
        System.out.println("==============>"+jsonObject);
        CommonUser user ;
//  把用户微信信息保存到数据库（判断这个信息是否存在，如果不存在，新增到数据库表（自动创建一个用户），如果已存在，直接登录成功）
        if (jsonObject.getString("openid") != null){
            user = cuService.selectByOpenId(openId);
            if (user == null) {
                switch (param){
                    case 1:
                        String name = jsonObject.getString("nickname");
                        String icon = jsonObject.getString("headimgurl");
                        String openid = jsonObject.getString("openid");
                        Byte status = 1;
                        Byte type = 2;
                        long time = new Date().getTime() / 1000;
                        user = new CommonUser(name, icon, openid, time, status, type);
                        cuService.insert(user);
                        break;
                    case 2:
                        user = (CommonUser) session.getAttribute("userinfo");
                        if (user != null){
                            user.setOpenid(openId);
                            cuService.update(user);
                        } else {
                            System.out.println("微信绑定——用户未登录");
                        }
                }
            }
            session.setAttribute("userinfo", user);


        }else {
            R result = R.error(jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
        }

        return "home";
    }

    @RequestMapping(value = "/send")
    public void send(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        try {
            String code = SendMessage.sendMess(phone);
            request.getSession().setAttribute("code", code);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            try {
                response.getWriter().write("发送失败了");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            response.getWriter().write("发送成功");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }




}
