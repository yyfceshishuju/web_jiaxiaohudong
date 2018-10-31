package com.jiaxiaohudong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

public class Wechat {

    private static final String APPID = "wxcf5e5cc1055dd608";
    private static final String SECRET = "bc00eedf0ff44bab004911579881347d";
    private static final String REDIRECT_URL = "10.236.95.249:8081/wechat/callBackLogin";
    private final static String AUTH_URL_OFFICIAL_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";
    private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    private final static String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
    private static final String RPLC="%s";

    public static String getOfficialAccAuthUrl(String redirect_url,String state)throws Exception{
        String url=AUTH_URL_OFFICIAL_URL;
        try {
            if(redirect_url!=null&&!"".equals(redirect_url)){
                url=url.replaceFirst(RPLC, APPID).replaceFirst(RPLC, URLEncoder.encode(redirect_url, "UTF-8")).replaceFirst(RPLC,state);
            }else{
                url=url.replaceFirst(RPLC, APPID).replaceFirst(RPLC, URLEncoder.encode(REDIRECT_URL,"UTF-8")).replaceFirst(RPLC,state);
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

}
