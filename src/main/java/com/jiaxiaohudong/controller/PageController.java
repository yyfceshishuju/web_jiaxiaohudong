package com.jiaxiaohudong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lcf12307 on 2018/10/27.
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String returnMainPage() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

}
