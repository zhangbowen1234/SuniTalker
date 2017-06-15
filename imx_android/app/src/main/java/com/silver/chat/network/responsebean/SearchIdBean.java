package com.silver.chat.network.responsebean;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/5/10.
 */

public class SearchIdBean implements Serializable {

    private String userId;
    private  String avatar;
    private  String mobile;
    private String nickName;
    private String nealName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNealName() {
        return nealName;
    }

    public void setNealName(String nealName) {
        this.nealName = nealName;
    }

    @Override
    public String toString() {
        return "SearchIdBean{" +
                "userId='" + userId + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickName='" + nickName + '\'' +
                ", nealName='" + nealName + '\'' +
                '}';
    }
}
