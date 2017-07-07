package com.silver.chat.network.responsebean;

/**
 * Created by bowen on 2017/7/7.
 */

public class UpdataLoadImg {
    private String url; //上传头像url

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UpdataLoadImg{" +
                "url='" + url + '\'' +
                '}';
    }
}
