package com.silver.chatsdk.service.bean;

/**
 * 作者：Fandy on 2016/12/2 12:01
 * 邮箱：fandy618@hotmail.com
 */

public class BaseResponse {

    private int statusCode;
    private String statusMsg;
    private long responseTime;

    public  BaseResponse(){

    }

    public BaseResponse(int code, String errMsg, long responseTime) {
        this.statusCode = code;
        this.statusMsg = errMsg;
        this.responseTime = responseTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
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
                "statusCode=" + statusCode +
                ", statusMsg='" + statusMsg + '\'' +
                ", responseTime=" + responseTime +
                '}';
    }
}

