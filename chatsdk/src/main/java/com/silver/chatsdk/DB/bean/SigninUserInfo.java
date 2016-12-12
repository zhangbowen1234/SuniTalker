package com.silver.chatsdk.db.bean;

/**
 * Created by ArthurYis on 2016/12/5.
 */

public class SigninUserInfo {
    private int userId;
    private String userName;
    private String avatar;

    public SigninUserInfo(int userId, String userName, String avatar) {
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
