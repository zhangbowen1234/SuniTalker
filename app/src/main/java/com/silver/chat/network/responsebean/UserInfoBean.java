package com.silver.chat.network.responsebean;

/**
 * Created by lenovo on 2017/5/3.
 */

public class UserInfoBean {

    private String mobile;
    private String avatar;
    private String nickName;
    private int sex;
    private int age;
    private String signature;
    private int level;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "mobile='" + mobile + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", signature='" + signature + '\'' +
                ", level=" + level +
                '}';
    }
}

