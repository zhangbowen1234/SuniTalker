package com.silver.chat.network.responsebean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/5/3.
 */

@DatabaseTable(tableName = "contact_list")
public class ContactListBean implements Serializable {
    @DatabaseField(id = true)
    private int friendId;
    @DatabaseField(columnName = "remarkName")
    private String remarkName;
    @DatabaseField(columnName = "avatar")
    private String avatar;
    @DatabaseField(columnName = "sex")
    private int sex;
    @DatabaseField(columnName = "nickName")
    private String nickName;
    @DatabaseField(columnName = "signature")
    private String signature;
    @DatabaseField(columnName = "sortLetters")
    private String sortLetters;  //显示数据拼音的首字母
    @DatabaseField(columnName = "userId")
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                ", userId='" + userId + '\'' +
                '}';
    }
}
