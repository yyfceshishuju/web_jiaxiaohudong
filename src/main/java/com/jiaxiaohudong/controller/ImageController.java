package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.baidu_service.Sample;
import com.jiaxiaohudong.entity.CommonCategory;
import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.util.R;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Request;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/image")
public class ImageController {

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public R uploadImage(@RequestParam(value = "file") MultipartFile file, Map<String, Object> map) throws RuntimeException {
        JSONObject json = null;
        if (file.isEmpty()) {
            json = new JSONObject("error");
        }else{
            // 获取文件名
            String fileName = file.getOriginalFilename();
            try {
                File f2 = new File(fileName);
                file.transferTo(f2);
                fileName = f2.getAbsolutePath();
                json =  Sample.fielToJson(fileName);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        JSONArray words_result = (JSONArray)json.get("words_result");
        List<Object> list = words_result.toList();
        String result = "";
        for (Object o: list){
            HashMap jo = (HashMap)o;
            result = result + jo.get("words").toString() + "\n\t";

        }
        map.put("result.size",result.getBytes().length);
        return R.ok(result);
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