package com.silver.chat.model;

/**
 * 作者：Fandy on 2016/12/2 12:01
 * 邮箱：fandy618@hotmail.com
 */

public class BaseResponse {

    private int statusCode;
    private String statusMsg;
    private long responseTime;

    public BaseResponse(){

    }

    public BaseResponse(int statusCode, String statusMsg, long responseTime) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.responseTime = responseTime;
    }

    public int getCode() {
        return statusCode;
    }

    public void setCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrMsg() {
        return statusMsg;
    }

    public void setErrMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + statusCode +
                ", errMsg='" + statusMsg + '\'' +
                ", responseTime=" + responseTime +
                '}';
    }
}

