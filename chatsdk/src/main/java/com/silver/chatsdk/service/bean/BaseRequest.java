package com.silver.chatsdk.service.bean;

/**
 * 作者：Fandy on 2016/12/5 17:53
 * 邮箱：fandy618@hotmail.com
 */

public class BaseRequest {

    private String cmd;
    private long requstTime;

    public BaseRequest(String cmd, long requstTime) {
        this.cmd = cmd;
        this.requstTime = requstTime;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public long getRequstTime() {
        return requstTime;
    }

    public void setRequstTime(long requstTime) {
        this.requstTime = requstTime;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "cmd='" + cmd + '\'' +
                ", requstTime=" + requstTime +
                '}';
    }
}
