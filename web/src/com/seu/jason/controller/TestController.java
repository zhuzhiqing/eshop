package com.seu.jason.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ToZhu on 2015/10/29.
 */
@Controller
@RequestMapping(value="/hello")
@ResponseBody
public class TestController {

    @RequestMapping(method = RequestMethod.GET )
    public String printWelcome(ModelMap model) {
       // model.addAttribute("message", "Spring 3 MVC Hello World");
        return "hello";
    }


}
