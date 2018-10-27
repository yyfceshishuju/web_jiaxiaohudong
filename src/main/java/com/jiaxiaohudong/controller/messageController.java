package com.jiaxiaohudong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yyf on 2018/10/17.
 */
@Controller
@RequestMapping("/message")
public class messageController {
    @RequestMapping("/go")
    public String goTest(){
        return "reach";
    }

    @RequestMapping("/detail/data={uname}")
    public String goDetail(@PathVariable("uname")String data, Model model){
        model.addAttribute("data", data);
        return "detail";
    }

    @RequestMapping(value = "/report1", method = RequestMethod.GET)
    public String reportGet(@RequestParam("begin") String begin,
                            @RequestParam("end") String end, Model model){
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        model.addAttribute("formType", "GET");
        return "report";
    }

    @RequestMapping(value = "/report2", method = RequestMethod.POST)
    public String reportPost(@RequestParam("begin") String begin,
                            @RequestParam("end") String end, Model model){
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        model.addAttribute("formType", "POST");
        return "report";
    }
}
