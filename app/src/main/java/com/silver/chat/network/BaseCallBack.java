package com.silver.chat.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.ui.login.LoginActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;

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
    public static <T> void enqueue(final Context context, Call<BaseResponse<T>> baseResponseCall, final ResponseCallBack<BaseResponse<T>> callBack) {
        baseResponseCall.enqueue(new Callback<BaseResponse<T>>() {
            @Override
            public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
                Log.e("BaseCallBack", response.body() + "");
                Log.e("BaseCallBack", response.body().data + "");

                if (response.body().getStatusCode() == 200) {
                    callBack.onSuccess(response.body());
                } else if (response.body().getStatusCode() == 300) {
                    PreferenceUtil.getInstance(context).setLog(false);
                    ToastUtil.toastMessage(context, "请重新登录");
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                } else {
                    callBack.onFailed(response.body());

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
                Log.e("BaseCallBack", t.toString());
                callBack.onError();
            }
        });
    }


    /**
     * 后台返回的数据没有data调用这个
     */
    public static <T> void enqueueBase(final Context context, Call<BaseResponse> baseResponseCall, final ResponseCallBack<BaseResponse> callBack) {
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Log.e("BaseCallBack", response.body() + "");
                if (response.body().getStatusCode() == 200 || response.body().getStatusCode() == 1) {
                    callBack.onSuccess(response.body());
                } else if (response.body().getStatusCode() == 300) {
                    PreferenceUtil.getInstance(context).setLog(false);
                    ToastUtil.toastMessage(context, "请重新登录");
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                } else {
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("BaseCallBack", t.toString());
                callBack.onError();
            }
        });
    }

}
