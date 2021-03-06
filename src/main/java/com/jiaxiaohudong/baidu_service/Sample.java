package com.jiaxiaohudong.baidu_service;

import com.baidu.aip.ocr.AipOcr;
import com.jiaxiaohudong.entity.CommonBaiduUser;
import com.jiaxiaohudong.entity.CommonQuestion;
import com.jiaxiaohudong.service.BaiduUserService;
import com.jiaxiaohudong.service.QuestionService;
import com.jiaxiaohudong.service.impl.QuestionServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class Sample {

    private static Sample Sample;
    public static QuestionService questionService;
    public static BaiduUserService baiduUserService;
    public static List<CommonBaiduUser> commonBaiduUsers;
    public static Integer index = -1;


    public static QuestionService getQuestionService() {
        return questionService;
    }

    public static void setQuestionService(QuestionService questionService) {
        com.jiaxiaohudong.baidu_service.Sample.questionService = questionService;
    }

    public static BaiduUserService getBaiduUserService() {
        return baiduUserService;
    }

    public static void setBaiduUserService(BaiduUserService baiduUserService) {
        com.jiaxiaohudong.baidu_service.Sample.baiduUserService = baiduUserService;
    }

    public void init(){
        Sample = this;
        Sample.questionService = this.questionService;
        Sample.baiduUserService =this.baiduUserService;

    }

    public static void main(String[] args) {

        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream("baidu.properties");
        try {
            System.out.println(is);
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(properties.getProperty("app_id").toString(), properties.getProperty("api_key").toString(), properties.getProperty("secret_key").toString());

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "log4j.properties");

        // 调用接口
        String path = "/Users/rizhiyi123/local/tmp1/javademo/web_jiaxiaohudong/src/main/resources/test3.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println("--------");
        System.out.println(res.get("words_result"));



    }

    public static JSONObject sample(String image, CommonBaiduUser commonBaiduUser){
        AipOcr client = new AipOcr(commonBaiduUser.getApp_id(), commonBaiduUser.getApi_key(), commonBaiduUser.getSecret_key());
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        JSONObject res = client.basicGeneral(image, options);
        System.out.println(res.toString());
        return res;
    }


    public static JSONObject sample(String image) {
       if(index == -1){
           commonBaiduUsers = baiduUserService.getAll();
           index = commonBaiduUsers.size();
       }
        CommonBaiduUser commonBaiduUser = commonBaiduUsers.get(new Random().nextInt(index));
       return sample(image,commonBaiduUser);
    }

    public static byte[] fileToByte(File img) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
    public static JSONObject fielToJson(String filepath) {
        return sample(filepath);

    }
    public static ImageTask imageTask = new ImageTask(new LinkedBlockingQueue<QuestionAndImage>());

    public static Runnable getService(){
        return new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("question 服务...");
                    try {
                        QuestionAndImage questionAndImage = imageTask.get();
                        String imagePath = questionAndImage.getImagePath();
                        System.out.println(imagePath);
                        CommonQuestion question = questionAndImage.getQuestion();
                        JSONObject json = sample(imagePath);
                        JSONArray words_result = (JSONArray) json.get("words_result");
                        List<Object> list = words_result.toList();
                        String result = "";
                        for (Object o : list) {
                            HashMap jo = (HashMap) o;
                            result = result + jo.get("words").toString() + "\n";

                        }
                        question.setQuestion(result);

                        questionService.modifyByPrimaryKey(question);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };
    }
    static {
        new Thread(getService()).start();
    }


}
