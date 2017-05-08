package com.silver.chat.network.responsebean;

/**
 * Created by lenovo on 2017/5/3.
 */

public class ContactListBean {

    private int FriendId;
    private String RemarkName;
    private String Avatar;
    private int Sex;
    private String NickName;
    private String Signature;

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public int getFriendId() {
        return FriendId;
    }

    public void setFriendId(int friendId) {
        FriendId = friendId;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String remarkName) {
        RemarkName = remarkName;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    @Override
    public String toString() {
        return "ContactListBean{" +
                "FriendId=" + FriendId +
                ", RemarkName='" + RemarkName + '\'' +
                ", Avatar='" + Avatar + '\'' +
                ", Sex=" + Sex +
                ", NickName='" + NickName + '\'' +
                ", Signature='" + Signature + '\'' +
                '}';
    }
}
