package com.silver.chat.network;

import android.util.Log;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hibon on 2017/5/13.
 * 联网请求的返回数据的回调封装
 */

public class BaseCallBack {
    /**
     * 后台返回的数据有data调用这个
     */
    public static <T>void enqueue(Call<BaseResponse<T>> baseResponseCall, final ResponseCallBack<BaseResponse<T>> callBack) {
        baseResponseCall.enqueue(new Callback<BaseResponse<T>>() {
            @Override
            public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
                Log.e("onResponse", response.body() + "");
                Log.e("onResponse", response.body().data + "");

                if (response.body().getStatusCode() == 200) {
                    callBack.onSuccess(response.body());
                }else {
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
                Log.e("aaa", t.toString());
                callBack.onError();
            }
        });
    }

    /**
     * 后台返回的数据没有data调用这个
     */
    public static <T>void enqueueBase(Call<BaseResponse> baseResponseCall, final ResponseCallBack<BaseResponse> callBack) {
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Log.e("response.body() =",response.body().toString());

                if (response.body().getStatusCode() == 200 || response.body().getStatusCode() ==1) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailed(response.body());
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("aaa", t.toString());
                callBack.onError();
            }
        });
    }

}
