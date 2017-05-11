package com.silver.chat.network.requestbean;

/**
 * Created by bowen on 2017/5/11.
 * 忘记密码
 */

public class ForgetPasswordBean {
    /**
     * smsCode : 464298
     * newPwd : 12345
     * reNewPwd : 12345
     * phone : 13121802155
     */

    private String smsCode;
    private String newPwd;
    private String reNewPwd;
    private String phone;
    private static ForgetPasswordBean mInstance;

    public static ForgetPasswordBean getInstance() {
        if (null == mInstance) {
            synchronized (ForgetPasswordBean.class) {
                if (null == mInstance) {
                    mInstance = new ForgetPasswordBean();
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

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getReNewPwd() {
        return reNewPwd;
    }

    public void setReNewPwd(String reNewPwd) {
        this.reNewPwd = reNewPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ForgetPasswordBean{" +
                "smsCode='" + smsCode + '\'' +
                ", newPwd='" + newPwd + '\'' +
                ", reNewPwd='" + reNewPwd + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
