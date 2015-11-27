package com.seu.jason.controller;

import com.seu.jason.service.IUserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by ToZhu on 2015/10/28.
 */

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Resource(name="userManager")
    IUserManager userManager;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object addUserInfo(@RequestHeader("phone_num") String phone_num,
                              @RequestHeader("password") String password,
                              @RequestHeader("code") String code){

        return userManager.registerUser(phone_num, password);
    }

    @RequestMapping(value = "/{user_id}",method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserInfo(@PathVariable("user_id") int user_id,
                                 @RequestParam (value = "file",required = false)MultipartFile avator,
                                 @RequestHeader("token") String token,
                                 @RequestHeader("name") String name){

        return userManager.updateUserInfo(user_id,name,token,avator);
    }


//    @RequestMapping(value = "/password",method = RequestMethod.PUT)
//    @ResponseBody
//    public Object updatePassword(@RequestHeader ("password") String password,
//                                 @RequestHeader("token") String token){
//
//
//    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public Object getUserInfo(@PathVariable("user_id") int user_id,
                              @RequestHeader("token") String token){

       return userManager.getUserInfo(user_id,token);
    }

    @RequestMapping(value = "/{user_id}/address",method = RequestMethod.POST)
    public Object addLocation(@PathVariable("user_id") int user_id,
                             @RequestHeader("token") String token,
                             @RequestHeader("location") String location,
                             @RequestHeader("name") String name,
                             @RequestHeader("gender") int gender,
                             @RequestHeader("phone") String phone,
                             @RequestHeader("room_number") String room_number){

       return userManager.addLocation(user_id, token, location, name, gender, phone, room_number);
    }

    @RequestMapping(value = "/{user_id}/address/{location_id}",method = RequestMethod.DELETE)
    public Object delLocation(@RequestHeader("token") String token,
                              @PathVariable("user_id") int user_id,
                              @RequestHeader("location_id") int location_id){

        return userManager.delLocation(user_id, location_id, token);

    }

    @RequestMapping(value = "/{user_id}/address/{location_id}",method = RequestMethod.PUT)
    public Object updateLocation(@RequestHeader("token") String token,
                                 @PathVariable("user_id") int user_id,
                                 @RequestHeader("location_id") int location_id,
                                 @RequestHeader("location") String location,
                                 @RequestHeader("name") String name,
                                 @RequestHeader("gender") int gender,
                                 @RequestHeader("phone") String phone,
                                 @RequestHeader("room_number") String room_number){
           return userManager.updateLoaction(token, user_id, location_id, location, name, gender, phone, room_number);
    }

    @RequestMapping(value = "/{user_id}/address/{location_id}",method = RequestMethod.GET)
    public Object getLoaction(@RequestHeader("token") String token,
                              @PathVariable("user_id") int user_id,
                              @RequestHeader("location_id") int location_id){

        return userManager.getLocation(token,user_id,location_id);

    }

}
