package com.silver.chatsdk.service.network;

/**
 * 作者：Fandy on 2016/12/2 11:55
 * 邮箱：fandy618@hotmail.com
 */

public interface ResponseCallBack<T> {
    void onSuccess(T t);

    void onFailed(int code);

}
