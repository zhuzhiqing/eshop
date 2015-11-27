package com.seu.jason.service;

import com.seu.jason.dao.IBaseDAO;
import com.seu.jason.entity.Location;
import com.seu.jason.entity.User;
import com.seu.jason.entity.UserSecretInfo;
import com.seu.jason.util.Constant;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by ToZhu on 2015/10/31.
 */
public class UserManager implements  IUserManager{

    @Autowired
    private IBaseDAO baseDAO;

    public void setBaseDAO(IBaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    public Object registerUser(String phone_num, String password){
        JSONObject response = new JSONObject();

        UserSecretInfo userSecretInfo = new UserSecretInfo();
        userSecretInfo.setPhone_num(phone_num);
        userSecretInfo.setPassword(password);
        String token = getUUID();
        userSecretInfo.setToken(token);

        User user = new User();
        user.setUserSecretInfo(userSecretInfo);
        user.setName(getRandomName());
        user.setReg_date(new Date(System.currentTimeMillis()));

        try {
           // baseDAO.saveOrUpdate(userSecretInfo);
            baseDAO.saveOrUpdate(user);
            response.put("request",0);
            response.put("user_id",userSecretInfo.getUser_id());
            response.put("name",user.getName());
        }catch (Exception ex){
            response.put("request",1);
            System.out.println(ex.getMessage());
        }finally {
            return response.toString();
        }
    }

    public Object updateUserInfo(int user_id,String token, String name, MultipartFile image){

        JSONObject response = new JSONObject();

        User user= (User)baseDAO.loadById(User.class, user_id);
        user.setName(name);

        //如果存在头像
        if(image!= null && !image.isEmpty() ) {
            StringBuilder path = new StringBuilder(Constant.PATH_WIN);
            String fileName = String.valueOf(user_id)+"_"
                    +new Date(System.currentTimeMillis())+"_"
                    +image.getOriginalFilename();
            try {
                saveImage(path.toString(),fileName,image);
                user.setImage(path.toString()+fileName);
            }catch (Exception e){
                response.put("request", 1);
                e.printStackTrace();
                return response.toString();
            }
        }

        try {
            baseDAO.saveOrUpdate(user);
            response.put("request", 0);
        }catch (Exception e){
            response.put("request", 1);
        }finally {
            return response.toString();
        }
    }

//    @Override
//    public Object updatePassword(int user_id,String password) {
//        JSONObject response = new JSONObject();
//
//        UserSecretInfo userSecretInfo = (UserSecretInfo)baseDAO.loadById(UserSecretInfo.class,user_id);
//        userSecretInfo.setPassword(password);
//        String new_token = getUUID();
//        userSecretInfo.setToken(new_token);
//
//
//
//
//        return null;
//    }

    public Object getUserInfo(int user_id, String token){
        JSONObject response = new JSONObject();

        User user = (User) baseDAO.loadById(User.class, user_id);
        response.put("request",0);
        response.put("name",user.getName());
        return response.toString();
    }

    public Object addLocation(int user_id, String token, String address, String name, int gender, String phone, String room_number){
        JSONObject response = new JSONObject();

        Location location = new Location();
        location.setName(name);
        location.setAddress(address);
        location.setRoom_number(room_number);
        location.setPhone(phone);
        location.setGender(gender);
//        location.setUser_id(user_id);

        try {
            baseDAO.saveOrUpdate(location);
            response.put("request",0);
            response.put("location_id",location.getLocation_id());
        }catch (Exception e){
            e.printStackTrace();
            response.put("request",1);
        }finally {
            return response;
        }
    }

    @Override
    public Object delLocation(int user_id, int location_id, String token) {
        JSONObject response = new JSONObject();

        try{
            baseDAO.delById(Location.class,location_id);
            response.put("request",0);
        }catch (Exception e){
            e.printStackTrace();
            response.put("request",1);
        }finally {
            return response.toString();
        }
    }

    @Override
    public Object updateLoaction(String token, int user_id, int location_id, String address, String name, int gender, String phone, String room_number) {
        JSONObject response = new JSONObject();

        UserSecretInfo userSecretInfo = (UserSecretInfo)baseDAO.loadById(UserSecretInfo.class, user_id);
        Location location = (Location) baseDAO.loadById(Location.class, location_id);
        location.setAddress(address);
        location.setName(name);
        location.setGender(gender);
        location.setPhone(phone);
        location.setRoom_number(room_number);
        location.setUserSecretInfo(userSecretInfo);
//        location.setUser_id(user_id);

        try {
            baseDAO.saveOrUpdate(location);
            response.put("request",0);
            response.put("location_id",location.getLocation_id());
        }catch (Exception e){
            e.printStackTrace();
            response.put("request",1);
        }finally {
            return response;
        }
    }

    @Override
    public Object getLocation(String token,int user_id, int location_id) {
        ResponseEntity responseEntity = new ResponseEntity();

        UserSecretInfo userSecretInfo = (UserSecretInfo) baseDAO.loadById(UserSecretInfo.class,user_id);

//        return userSecretInfo.getLocations();

        return null;
//        try{
//            Location location = (Location) baseDAO.loadById(Location.class, location_id);
//            response.put("request",0);
////            response.put("")
//        }catch (Exception e){
//            e.printStackTrace();
//            response.put("request",1);
//        }finally {
//            return response.toString();
//        }
    }

    public void saveImage(String path,String fileName,MultipartFile image) throws Exception{
        File targetFile = new File(path,fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //save
        image.transferTo(targetFile);
    }

    public int findUserByToken(String token){
        List<Object> list= baseDAO.listAll(UserSecretInfo.class.getName(),new String []{"token"},new String []{token});
        if(list == null || list.size()<1){
            return -1;
        }

        UserSecretInfo userSecretInfo = (UserSecretInfo)list.get(0);
        return userSecretInfo.getUser_id();
    }

    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }

    public static String getRandomName(){
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        for(int i=0;i<2;i++){
            name.append((char)(random.nextInt(26)+'a'));
        }
        name.append('_');
        for(int i=0;i<6;i++){
            name.append((char)(random.nextInt(9)+'0'));
        }

        return name.toString();
    }
}
