package com.jiaxiaohudong.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
/**
 *
 * Created by lcf12307 on 2018/10/28.
 */

@Controller
@RequestMapping("/wechat")
public class WechatController {


    @RequestMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String url = "https://open.weixin.qq.com/connect/qrconnect?";
        url += "appid=你的appid";


        url += "&redirect_uri=" + URLEncoder.encode("http://jiaxiao.com/wechat/callBackLogin", "UTF-8");
        url += "&response_type=code&scope=snsapi_login";
        url += "&state=" + request.getSession().getId() + "#wechat_redirect";
//redirect_uri；指定回调路径
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/callBackLogin")
    public String callBackLogin(HttpServletRequest request, HttpServletResponse response,Model model) {
        System.out.println("callBackLogin....");
// return "redirect:../loginSuccess.jsp";
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        System.out.println("code=" + code);
        System.out.println("state=" + state);


        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?";
        url += "appid=你的appid";
        url += "&secret=你的secret";


        url += "&code=" + code + "&grant_type=authorization_code";


        JSONObject jsonObject = this.httpGet(url);
        String at = jsonObject.getString("access_token");//获取微信开放平台票据号
        String openId = jsonObject.getString("openid");

        url="https://api.weixin.qq.com/sns/userinfo?access_token="+at+"&openid="+openId;

        jsonObject = this.httpGet(url);
        System.out.println("==============>"+jsonObject);
        model.addAttribute("weixin", jsonObject);
// TODO 把用户微信信息保存到数据库（判断这个信息是否存在，如果不存在，新增到数据库表（自动创建一个用户），如果已存在，直接登录成功）

        return "index";
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
