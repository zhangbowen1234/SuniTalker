package com.example.factory.presistence;

/**
 * Created by bowen on 2018/3/13.
 */

public class Account {
    // 设备的推送Id
    private static String pushId = "test";

    public static void setPushId(String pushId){
        Account.pushId = pushId;
    }
    public static String getPushId(){
        return pushId;
    }
}
