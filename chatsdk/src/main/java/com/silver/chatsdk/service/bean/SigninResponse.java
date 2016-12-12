package com.silver.chatsdk.service.bean;


import com.silver.chatsdk.db.bean.SigninUserInfo;

/**
 * Created by ArthurYis on 2016/12/5.
 */

public class SigninResponse extends BaseResponse {

    private SigninUserInfo user;

    public SigninUserInfo getUser() {
        return user;
    }

    public void setUser(SigninUserInfo user) {
        this.user = user;
    }

}
