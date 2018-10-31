package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.baidu_service.Sample;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/image")
public class ImageController {

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public void uploadImage(@RequestParam(value = "file") MultipartFile file, PrintWriter printWriter, Map<String, Object> map) throws RuntimeException {
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
        printWriter.write(result);

    }

    @RequestMapping(value = "/class.do", method = RequestMethod.GET)
    public String toClass(){ return "test"; }



}
