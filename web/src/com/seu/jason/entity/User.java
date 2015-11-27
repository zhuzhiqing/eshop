package com.seu.jason.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ToZhu on 2015/10/29.
 */

/**
 * Hiernate 数据库模型
 */
@Entity
@Table (name="user_info")
public class User {

    @Id
    //共享主键从表，主键来自于主表
    @GeneratedValue (generator ="foreignKey")
    @GenericGenerator (
            name = "foreignKey" ,
            strategy = "foreign" ,
            parameters = @Parameter(name = "property" , value = "userSecretInfo" )
    )
    private int user_id;

    @Column
    String image;

    @Column(length=24)
    String name;

    @Column
    Date reg_date;


    //这里用的是PrimaryKeyJoinColumn,不是JoinColumn
    //级联新建级联删除
    @OneToOne(targetEntity=UserSecretInfo.class, cascade={CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private UserSecretInfo userSecretInfo;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public UserSecretInfo getUserSecretInfo() {
        return userSecretInfo;
    }

    public void setUserSecretInfo(UserSecretInfo userSecretInfo) {
        this.userSecretInfo = userSecretInfo;
    }
}
