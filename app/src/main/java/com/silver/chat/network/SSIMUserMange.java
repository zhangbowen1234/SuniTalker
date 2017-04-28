package com.silver.chat.network;

import android.util.Log;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.LoginRequest;
import com.silver.chat.network.responsebean.LoginRequestBean;
import com.silver.chat.network.responsebean.RegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by joe on 2017/4/26.
 */

public class SSIMUserMange {

    /**
     * 验证手机号是否注册
     * @param version 版本号当前为leaf
     * @param phone 手机号
     * @param callBack
     */
    public static void checkPhone(String version,String phone,final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.checkPhoneCode(version,phone);
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getStatusCode() == 1 || response.body().getStatusCode() == 2) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailed(response.body());
                }
                Log.e("response", response.body().toString());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onError();
            }
        });
    }
    /**
     * 发送验证码
     * @param version 版本号当前为leaf
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
                    callBack.onFailed(response.body());
                }
                //Log.e("RegisterPhoneActivity", response.body().toString() );
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onError();
            }
        });
    }

    /**
     * 注册
     * @param version
     * @param request 注册对象
     * @param callBack
     */
    public static void goReginst(String version, RegisterRequest request, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.registPhone(version,request);
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());

                }else{
                    callBack.onFailed(response.body());
                }
                Log.e("response", response.body().toString());

            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onError();
            }
        });
    }

    /**
     * 登录
     * @param version
     * @param loginRequest 登录对象
     * @param callBack
     */
    public static void goLogin(String version, LoginRequest loginRequest, final ResponseCallBack<BaseResponse<LoginRequestBean>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<LoginRequestBean>> baseResponseCall = imApi.goLogin(version,loginRequest);
        baseResponseCall.enqueue(new Callback<BaseResponse<LoginRequestBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginRequestBean>> call, Response<BaseResponse<LoginRequestBean>> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginRequestBean>> call, Throwable t) {

            }
        });
    }


}
