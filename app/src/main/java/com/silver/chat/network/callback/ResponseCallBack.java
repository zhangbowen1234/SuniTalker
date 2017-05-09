package com.silver.chat.network.callback;

import com.silver.chat.entity.GroupBean;
import com.silver.chat.network.responsebean.BaseResponse;

import java.util.ArrayList;

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
     * @param t
     */
    void onFailed(T t);

    /**
     * 请求失败时的回调
     * @param
     */
    void onError();

}
