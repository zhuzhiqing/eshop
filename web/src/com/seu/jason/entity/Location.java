package com.seu.jason.entity;

/**
 * Created by ToZhu on 2015/11/17.
 */
import javax.persistence.*;

@Entity
@Table(name="location")
public class Location {

    @Id
    @GeneratedValue
    int location_id;

//    @Column
//    int user_id;

    @Column
    String address;

    @Column
    String name;

    @Column
    int gender;

    @Column
    String phone;

    @Column
    String room_number;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserSecretInfo userSecretInfo;

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public UserSecretInfo getUserSecretInfo() {
        return userSecretInfo;
    }

    public void setUserSecretInfo(UserSecretInfo userSecretInfo) {
        this.userSecretInfo = userSecretInfo;
    }
}
