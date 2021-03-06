package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.baidu_service.Sample;
import com.jiaxiaohudong.entity.CommonCategory;
import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.util.R;
import com.jiaxiaohudong.util.Upload;
import org.apache.log4j.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/image")
public class ImageController {
    private static Logger logger = Logger.getLogger(ImageController.class.getName());

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public R uploadImage(@RequestParam(value = "file") MultipartFile file, Map<String, Object> map) throws RuntimeException {
        String imagePath = null;
        if (file.isEmpty()) {
           return R.error("空文件");
        }else{
            String fileName = file.getOriginalFilename();
            logger.debug(fileName);
            try {
                imagePath = "/root/utils/tomcat-7.0/webapps/upload/"+new Date().getTime() + fileName;
                File f2 = new File(imagePath);
                file.transferTo(f2);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        map.put("json.size",imagePath.getBytes().length);
        System.out.println("ImageController ..."+imagePath);
        return R.ok(imagePath);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public R upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws RuntimeException {
        JSONObject json = null;
        if (file.isEmpty()) {
            return R.error(-2, "文件不能为空");
        }else{

            long time = new Date().getTime() / 1000;
            String icon = Upload.upload(time, file, request);
            return R.ok(icon);
        }

    }
    @RequestMapping(value = "/class.do", method = RequestMethod.GET)

    public String toClass(){ return "test"; }

    @RequestMapping(value = "/example1", method = RequestMethod.GET)
    @ResponseBody
    public R example1(){
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<CommonStudent> students = new ArrayList<CommonStudent>();
        CommonStudent cs = new CommonStudent("小明",1, "/img/logo.png");
        students.add( cs);
        cs = new CommonStudent("小华", 2, "/img/logo.png");
        students.add(cs);
        result.put("code", 0);
        result.put("data", students);
        return R.ok(result);
    }
    @RequestMapping(value = "/example2", method = RequestMethod.GET)
    @ResponseBody
    public R example2(){
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<CommonCategory> categorys = new ArrayList<CommonCategory>();
        CommonCategory cs = new CommonCategory(1, "语文");
        categorys.add( cs);
        cs = new CommonCategory(2, "数学");
        categorys.add(cs);
        result.put("code", 0);
        result.put("data", categorys);
        return R.ok(result);
    }

    @RequestMapping(value = "/example3", method = RequestMethod.GET)
    @ResponseBody
    public R example3(){
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<CommonStudent> students = new ArrayList<CommonStudent>();
        CommonStudent cs = new CommonStudent("谢耳朵",3, "/img/logo.png");
        students.add( cs);
        cs = new CommonStudent("小爱", 4, "/img/logo.png");
        students.add(cs);
        result.put("code", 0);
        result.put("data", students);
        return R.ok(result);
    }
}