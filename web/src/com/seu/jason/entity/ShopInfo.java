package com.seu.jason.entity;

import javax.persistence.*;

/**
 * Created by ToZhu on 2015/11/17.
 */

@Entity
@Table(name = "shop_info")
public class ShopInfo {

    @Id
    @GeneratedValue
    int shop_id;

    @Column
    String name;

    @Column
    int user_id;

    @Column
    String owner_name;

    @Column
    String phone;

    @Column
    int category;

    @Column
    String image;

    @Column
    String desc;

    @Column
    String address;


    static enum CATEGORY{
        CATEGORY_TYPE_RESTURAN,
        CATEGORY_TYPE_DISSERT
    }
}
