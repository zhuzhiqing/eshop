package com.seu.jason.controller;

import com.seu.jason.service.IUserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by ToZhu on 2015/11/27.
 */

@Controller
@RequestMapping("/qiniu")
@ResponseBody
public class QiNiuController {

    @Resource(name="userManager")
    IUserManager userManager;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object handle(@RequestParam String param){
        return null;
    }

}
