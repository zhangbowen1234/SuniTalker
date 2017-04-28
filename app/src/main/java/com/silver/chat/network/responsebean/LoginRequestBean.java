package com.silver.chat.network.responsebean;

import java.io.Serializable;

/**
 * Created by ArthurYis on 2016/12/5.
 */

public class LoginRequestBean implements Serializable{
    private int userId;
    private  String token;
    private  String imToken;
    private int imUserId;
    private String avatar;
    private String nickName;


    private static LoginRequestBean mInstance;
    public static LoginRequestBean getInstance() {
        if (null == mInstance) {
            synchronized (LoginRequestBean.class) {
                if (null == mInstance) {
                    mInstance = new LoginRequestBean();
                }
            }
        }
        return mInstance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public int getImUserId() {
        return imUserId;
    }

    public void setImUserId(int imUserId) {
        this.imUserId = imUserId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static LoginRequestBean getmInstance() {
        return mInstance;
    }

    public static void setmInstance(LoginRequestBean mInstance) {
        LoginRequestBean.mInstance = mInstance;
    }

    @Override
    public String toString() {
        return "LoginRequestBean{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", imToken='" + imToken + '\'' +
                ", imUserId=" + imUserId +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
