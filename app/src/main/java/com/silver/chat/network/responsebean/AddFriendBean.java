package com.silver.chat.network.responsebean;


import java.io.Serializable;

/**
 * Created by hibon on 2017/4/28.
 */

public class AddFriendBean implements Serializable{
    private int code;
    private String errMsg;
    private long responseTime;

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

    @Override
    public String toString() {
        return "AddFriendBean{" +
                "code=" + code +
                ", errMsg='" + errMsg + '\'' +
                ", responseTime=" + responseTime +
                '}';
    }
}
