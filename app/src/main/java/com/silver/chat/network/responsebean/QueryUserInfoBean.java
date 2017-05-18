package com.silver.chat.network.responsebean;

import java.io.Serializable;

/**
 * Created by hibon on 2017/5/18.
 */

public class QueryUserInfoBean implements Serializable {

    private String  avatar;
    private String nickName;
    private int sex;
    private int age;
    private String signature;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "QueryUserInfoBean{" +
                "avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", signature='" + signature + '\'' +
                '}';
    }
}
