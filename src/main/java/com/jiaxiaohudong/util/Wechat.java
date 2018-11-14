package com.jiaxiaohudong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;

public class Wechat {

    private static final String APPID = "wx3d0ec63734e74876";
    private static final String SECRET = "59f7ee88b51b0bf3cfe1237fac96c4e4";
    private static final String REDIRECT_URL = "http://www.jiaxiaoshequ.com/mp/callBackLogin";
    private static final String REDIRECT_BIND_URL = "http://www.jiaxiaoshequ.com/mp/callBackBind";
    private final static String AUTH_URL_OFFICIAL_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s&param=%s#wechat_redirect";
    private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    private final static String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
    private static final String RPLC="%s";

    public static String getOfficialAccAuthUrl(String redirect_url,String state, int param)throws Exception{
        String url=AUTH_URL_OFFICIAL_URL;
        try {
            if(redirect_url!=null&&!"".equals(redirect_url)){
                url=url.replaceFirst(RPLC, APPID).replaceFirst(RPLC, URLEncoder.encode(redirect_url, "UTF-8")).replaceFirst(RPLC,state);
            }else{
                redirect_url = param == 1? REDIRECT_URL:REDIRECT_BIND_URL;
                url=url.replaceFirst(RPLC, APPID).replaceFirst(RPLC, URLEncoder.encode(redirect_url,"UTF-8")).replaceFirst(RPLC,state);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return url;
    }

    public static String getUserInfoUrl(String token, String openid){
        String url = USER_INFO_URL;
        url = url.replaceFirst(RPLC, token).replaceFirst(RPLC, openid);
        return url;
    }

    public static String getTokenUrl(String code){
        String url = ACCESS_TOKEN_URL;
        url = url.replaceFirst(RPLC, APPID).replaceFirst(RPLC, SECRET).replaceFirst(RPLC, code);
        return url;
    }

    public static JSONObject httpGet(String url) {
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

    public static JSONObject getUserInfo(HttpServletRequest request){
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
            return null;
        }
        url=Wechat.getUserInfoUrl(at, openId);
        jsonObject = Wechat.httpGet(url);
        System.out.println("==============>"+jsonObject);
        return jsonObject;
    }

}
