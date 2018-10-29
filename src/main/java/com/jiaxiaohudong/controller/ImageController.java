package com.jiaxiaohudong.controller;

import com.jiaxiaohudong.baidu_service.Sample;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@Controller
@RequestMapping("/image")
public class ImageController {

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public JSONObject uploadImage(@RequestParam(value = "file") MultipartFile file, Map<String,Object> map) throws RuntimeException {
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
        Object result = json.get("words_result");
        map.put("result",result);
        return new JSONObject(json.get("words_result"));
    }



}
