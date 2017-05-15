package com.silver.chat.network.responsebean;

/**
 * Created by lenovo on 2017/5/3.
 */

public class ContactListBean {

    private int friendId;
    private String remarkName;
    private String avatar;
    private int sex;
    private String nickName;
    private String signature;
    private String sortLetters;  //显示数据拼音的首字母

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    @Override
    public String toString() {
        return "ContactListBean{" +
                "friendId=" + friendId +
                ", remarkName='" + remarkName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", nickName='" + nickName + '\'' +
                ", signature='" + signature + '\'' +
                ", sortLetters='" + sortLetters + '\'' +
                '}';
    }
}
