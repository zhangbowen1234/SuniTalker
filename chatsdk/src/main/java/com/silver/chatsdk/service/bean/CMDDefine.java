package com.silver.chatsdk.service.bean;

/**
 * Created by ArthurYis on 2016/12/5.
 */

public class CMDDefine {
    final static String REGISTER = "REGISTER";
    final static String SIGNIN = "SIGNIN";
    static long getNowTime(){
        return  System.currentTimeMillis();
    }
}
