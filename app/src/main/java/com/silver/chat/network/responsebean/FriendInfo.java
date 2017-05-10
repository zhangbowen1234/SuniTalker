package com.silver.chat.network.responsebean;

/**
 * Created by bowen on 2017/5/9.
 */

public class FriendInfo {
    private String RemarkName;
    private String LoginName;
    private String Avatar;

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String remarkName) {
        RemarkName = remarkName;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    @Override
    public String toString() {
        return "FriendInfo{" +
                "RemarkName='" + RemarkName + '\'' +
                ", LoginName='" + LoginName + '\'' +
                ", Avatar='" + Avatar + '\'' +
                '}';
    }
}
