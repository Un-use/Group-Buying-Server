package com.unuse.user.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by unuse on 17/2/16.
 */

public class UserData implements Serializable {

    private Long uid;

    private String phone;

    private String name;

    private String username;

    private String gender;

    private String avatar;

    private Float funds;

    private Date createTime;

    private Date updateTime;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Float getFunds() {
        return funds;
    }

    public void setFunds(Float funds) {
        this.funds = funds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static UserData makeUserData(User user) {
        UserData userData = null;

        if (null != user) {
            userData = new UserData();
            userData.setAvatar(user.getAvatar());
            userData.setCreateTime(user.getCreateTime());
            switch (Integer.valueOf(user.getGender())) {
                case 0:
                    userData.setGender("未设置");
                    break;
                case 1:
                    userData.setGender("男");
                    break;
                case 2:
                    userData.setGender("女");
                    break;
            }
            userData.setPhone(user.getPhone());
            userData.setUsername(user.getUsername());
            userData.setName(user.getName());
            userData.setUid(user.getUid());
            userData.setUpdateTime(user.getUpdateTime());
            userData.setFunds(user.getFunds());
        }

        return userData;
    }
}
