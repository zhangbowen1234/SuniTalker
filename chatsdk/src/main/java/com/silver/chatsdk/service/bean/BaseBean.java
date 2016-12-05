package com.silver.chatsdk.service.bean;

/**
 * 作者：Fandy on 2016/12/2 12:01
 * 邮箱：fandy618@hotmail.com
 */

public class BaseBean {

    private int code;
    private String errMsg;


    public BaseBean(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}

