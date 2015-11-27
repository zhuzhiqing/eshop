package com.seu.jason.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ToZhu on 2015/10/31.
 */
public interface IUserManager {
    public Object registerUser(String phone_num, String password);

    public int findUserByToken(String token);

    public Object updateUserInfo(int user_id, String token, String name, MultipartFile image);

//    public Object updatePassword(int user_id, String password,String token);

    public Object getUserInfo(int user_id, String token);

    public Object addLocation(int user_id, String token, String address, String name, int gender, String phone, String room_number);

    public Object delLocation(int user_id, int location_id, String token);

    public Object updateLoaction(String token, int user_id, int location_id, String address, String name, int gender, String phone, String room_number);

    public Object getLocation(String token, int user_id, int location_id);
}
