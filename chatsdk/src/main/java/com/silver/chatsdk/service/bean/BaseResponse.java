package com.silver.chatsdk.service.bean;

/**
 * 作者：Fandy on 2016/12/2 12:01
 * 邮箱：fandy618@hotmail.com
 */

public class BaseResponse {

    private int code;
    private String errMsg;
    private long responseTime;

    public BaseResponse(int code, String errMsg, long responseTime) {
        this.code = code;
        this.errMsg = errMsg;
        this.responseTime = responseTime;
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

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}

