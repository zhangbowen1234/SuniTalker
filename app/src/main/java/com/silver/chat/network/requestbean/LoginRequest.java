package com.silver.chat.network.requestbean;

import java.io.Serializable;

/**
 * Created by ArthurYis on 2016/12/5.
 */

public class LoginRequest implements Serializable{
    private String smsCode;
    private  String phone;
    private  String password;
    private String email;
    private String phoneUuid;
    private static LoginRequest mInstance;

    public static LoginRequest getInstance() {
        if (null == mInstance) {
            synchronized (LoginRequest.class) {
                if (null == mInstance) {
                    mInstance = new LoginRequest();
                }
            }
        }
        return mInstance;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneUuid() {
        return phoneUuid;
    }

    public void setPhoneUuid(String phoneUuid) {
        this.phoneUuid = phoneUuid;
    }

    public static LoginRequest getmInstance() {
        return mInstance;
    }

    public static void setmInstance(LoginRequest mInstance) {
        LoginRequest.mInstance = mInstance;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "smsCode='" + smsCode + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneUuid='" + phoneUuid + '\'' +
                '}';
    }
}
