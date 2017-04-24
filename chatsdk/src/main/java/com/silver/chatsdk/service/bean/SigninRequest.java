package com.silver.chatsdk.service.bean;

/**
 * Created by ArthurYis on 2016/12/5.
 */

public class SigninRequest extends BaseRequest {

    private String loginName;
    private String pwd;



    public SigninRequest(String loginName, String pwd) {
        super(CMDDefine.SIGNIN, CMDDefine.getNowTime());
        this.loginName = loginName;
        this.pwd = pwd;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

