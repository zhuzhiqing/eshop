package com.seu.jason.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by ToZhu on 2015/10/31.
 * 创建用户私密信息的Model
 */

@Entity
@Table(name="user_scret_info")
public class UserSecretInfo {

    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    //自动增长模式，数据库须设计成自增
    @GeneratedValue
    int user_id;

    @Column
    String phone_num;

    @Column
    String password;

    @Column
    String token;

    @Column
    Date expire;

    @OneToOne(targetEntity=User.class, cascade={CascadeType.ALL}, mappedBy = "userSecretInfo")
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany()
    private Set<Location> locations;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
}
