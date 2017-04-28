package com.silver.chat.network;

import android.util.Log;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by joe on 2017/4/26.
 */

public class SSIMUserMange {

    /**
     * 发送验证码
     * @param version 版本号 当前为leaf
     * @param phone 手机号
     * @param genre 请求种类
     * @param callBack 请求回调
     */
    public static void userReginstCode(String version,String phone,int genre,final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.registCode(version,phone,genre);
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body().getStatusCode());
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
            }
        });
    }
}
