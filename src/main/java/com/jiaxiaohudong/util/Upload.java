package com.jiaxiaohudong.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class Upload {

    public static String upload(long time, MultipartFile uploaderInput, HttpServletRequest request){
        String fileName = "";
        String orignName = "";
        if (uploaderInput == null || uploaderInput.isEmpty()) {
            orignName = "/img/logo.png";
        }else{
            // 获取文件名
            String path = Translate.getPath(request);
            orignName = uploaderInput.getOriginalFilename();
            fileName = time + "." + orignName.substring(orignName.lastIndexOf(".") + 1);
            System.out.println(fileName);
            orignName = "/upload/" + fileName;
            try {
                File f2 = new File(path, fileName);
                uploaderInput.transferTo(f2);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return orignName;
    }
}
