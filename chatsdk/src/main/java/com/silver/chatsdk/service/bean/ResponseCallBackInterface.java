package com.silver.chatsdk.service.bean;

/**
 * 作者：Fandy on 2016/12/2 11:55
 * 邮箱：fandy618@hotmail.com
 */

public interface ResponseCallBackInterface<T> {
    /**
     * 请求成功
     * 返回code为1时的回调
     * @param t
     */
    void onSuccess(T t);

    /**
     * 请求成功
     * 返回code不为1时的回调
     * @param code
     */
    void onFailed(int code);

    /**
     * 请求失败时的回调
     */
    void onError();
}
