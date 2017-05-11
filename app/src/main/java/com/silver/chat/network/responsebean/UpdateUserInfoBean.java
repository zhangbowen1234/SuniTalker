package com.silver.chat.network.responsebean;

/**
 * Created by bowen on 2017/5/11.
 * 修改用户
 */

public class UpdateUserInfoBean {

    /**
     * nickName : joker
     * sex : 1
     * age : 12
     * signature : 生命在于运动
     */

    private String nickName;
    private int sex;
    private int age;
    private String signature;
    private static UpdateUserInfoBean mInstance;
    public static UpdateUserInfoBean getInstance() {
        if (null == mInstance) {
            synchronized (UserInfoBean.class) {
                if (null == mInstance) {
                    mInstance = new UpdateUserInfoBean();
                }
            }
        }
        return mInstance;
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
        return "UpdateUserInfoBean{" +
                "nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", signature='" + signature + '\'' +
                '}';
    }

}

