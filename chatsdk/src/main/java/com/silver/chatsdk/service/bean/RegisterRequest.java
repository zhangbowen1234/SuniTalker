package com.silver.chatsdk.service.bean;

/**
 * Created by ArthurYis on 2016/12/5.
 */

public class RegisterRequest extends BaseRequest {
    private String loginName;
    private  String pwd;
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RegisterRequest(String loginName, String pwd, String code) {
        super(CMDDefine.REGISTER, CMDDefine.getNowTime());
        this.loginName = loginName;
        this.pwd = pwd;
        this.code = code;
    }
}
