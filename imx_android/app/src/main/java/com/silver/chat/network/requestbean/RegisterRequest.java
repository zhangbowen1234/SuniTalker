package com.silver.chat.network.requestbean;

import java.io.Serializable;

/**
 * Created by ArthurYis on 2016/12/5.
 */

public class RegisterRequest implements Serializable{
    private String smsCode;
    private  String phone;
    private  String pws;
    private String repws;
    private static RegisterRequest mInstance;

    public static RegisterRequest getInstance() {
        if (null == mInstance) {
            synchronized (RegisterRequest.class) {
                if (null == mInstance) {
                    mInstance = new RegisterRequest();
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

    public String getRepws() {
        return repws;
    }

    public void setRepws(String repws) {
        this.repws = repws;
    }

    public String getPws() {
        return pws;
    }

    public void setPws(String pws) {
        this.pws = pws;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "smsCode='" + smsCode + '\'' +
                ", phone='" + phone + '\'' +
                ", pws='" + pws + '\'' +
                ", repws='" + repws + '\'' +
                '}';
    }
}
