package com.jiaxiaohudong.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.service.CommonUserService;
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
import java.util.Date;

/**
 *
 * Created by lcf12307 on 2018/10/28.
 */

@Controller
@RequestMapping("/wechat")
public class WechatController {


    @Autowired
    private CommonUserService cuService;

    @RequestMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        try {
            String url = Wechat.getOfficialAccAuthUrl(null, request.getSession().getId());
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/callBackLogin")
    public String callBackLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println("callBackLogin....");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        System.out.println("code=" + code);
        System.out.println("state=" + state);


        String url = Wechat.getTokenUrl(code);

        JSONObject jsonObject = this.httpGet(url);
        String at = jsonObject.getString("access_token");//获取微信开放平台票据号
        String openId = jsonObject.getString("openid");
        if (at == null || openId == null){
            return "home";
        }
        url=Wechat.getUserInfoUrl(at, openId);

        jsonObject = this.httpGet(url);
        System.out.println("==============>"+jsonObject);
        model.addAttribute("weixin", jsonObject);
//  把用户微信信息保存到数据库（判断这个信息是否存在，如果不存在，新增到数据库表（自动创建一个用户），如果已存在，直接登录成功）
        CommonUser user ;
        if (jsonObject.getString("openid") != null){
            user = cuService.selectByOpenId(openId);
            if (user == null){
                String name = jsonObject.getString("nickname");
                String icon = jsonObject.getString("headimgurl");
                String openid = jsonObject.getString("openid");
                Byte status = 1;
                Byte type = 2;
                long time = new Date().getTime() / 1000;
                user = new CommonUser(name, icon, openid, time, status, type);
                cuService.insert(user);
            }
            session.setAttribute("userinfo", user);
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

    private JSONObject httpGet(String url) {
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity(),"UTF-8");
                jsonResult = JSON.parseObject(strResult);
                System.out.println("strResult=" + strResult);
            } else {
                System.out.println("*******************");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }


}
