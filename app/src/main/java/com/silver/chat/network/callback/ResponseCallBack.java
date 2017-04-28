package com.silver.chat.network.callback;

/**
 * Created by Joe on 2017/4/27.
 */

public interface ResponseCallBack<T> {
    /**
     * 请求成功
     *
     * @param t
     */
    void onSuccess(T t);

    /**
     *
     * @param code
     */
    void onFailed(int code);

    /**
     * 请求失败时的回调
     */
    void onError();

}
