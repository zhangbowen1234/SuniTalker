package com.silver.chat.network;


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
