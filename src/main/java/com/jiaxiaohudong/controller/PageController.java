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

    @RequestMapping(value = "/message/report1", method = RequestMethod.GET)
    public String reportGet(@RequestParam("begin") String begin,
                            @RequestParam("end") String end, Model model){
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        model.addAttribute("formType", "GET");
        return "report";
    }

    @RequestMapping(value = "/message/report2", method = RequestMethod.POST)
    public String reportPost(@RequestParam("begin") String begin,
                             @RequestParam("end") String end, Model model){
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        model.addAttribute("formType", "POST");
        return "report";
    }
}
