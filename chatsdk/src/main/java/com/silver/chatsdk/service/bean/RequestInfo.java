package com.silver.chatsdk.service.bean;

/**
 * 作者：Fandy on 2016/12/2 11:24
 * 邮箱：fandy618@hotmail.com
 */
public class RequestInfo {
    private String cmd;
    private String mobile;
    private String userPwd;
    private String email;


    public RequestInfo(String cmd, String mobile, String userPwd, String email) {
        this.cmd = cmd;
        this.mobile = mobile;
        this.userPwd = userPwd;
        this.email = email;
    }
}

