package com.silver.chat.network;

import android.util.Log;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.ForgetPasswordBean;
import com.silver.chat.network.requestbean.LoginRequest;
import com.silver.chat.network.requestbean.RegisterRequest;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.FriendInfo;
import com.silver.chat.network.responsebean.LoginRequestBean;
import com.silver.chat.network.responsebean.UpdateUserInfoBean;
import com.silver.chat.network.responsebean.UserInfoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hibon on 2017/4/26.
 * 登录注册请求
 */

public class SSIMLoginManger {

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
                if (response.body().getStatusCode() == 200 || response.body().getStatusCode() == 2) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailed(response.body());
                }
                //Log.e("response", response.body().toString());
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
                callBack.onError();
            }
        });
    }


    /**
     * 获取用户信息
     * @param version
     * @param token
     * @param callBack
     */
    public static void getUserInfo(String version, String token, final ResponseCallBack<BaseResponse<UserInfoBean>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<UserInfoBean>> baseResponseCall = imApi.userInfo(version, token);
        baseResponseCall.enqueue(new Callback<BaseResponse<UserInfoBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserInfoBean>> call, Response<BaseResponse<UserInfoBean>> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UserInfoBean>> call, Throwable t) {
                callBack.onError();
            }
        });
    }

    /**
     * 获取好友信息
     * @param version
     * @param userId 当前用户id
     * @param friendid 好友id
     * @param token
     * @param callBack
     */
    public static void friendinfo(String token,String version, String userId, String friendid,
                                  final ResponseCallBack<BaseResponse<List<FriendInfo>>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<List<FriendInfo>>> baseResponseCall = imApi.friendinfo(token,version,userId,friendid);
        baseResponseCall.enqueue(new Callback<BaseResponse<List<FriendInfo>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FriendInfo>>> call, Response<BaseResponse<List<FriendInfo>>> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FriendInfo>>> call, Throwable t) {
                callBack.onError();
            }
        });
    }
    /**
     * 退出登录
     * @param version
     * @param token
     * @param callBack
     */
    public static void outLogin(String version, String token, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.outLogin(version,token);
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onError();
            }
        });
    }
    /**
     * 修改好友信息
     * @param version
     * @param userInfoBean 实体类
     * @param token
     * @param callBack
     */
    public static void updateUserInfo(String version, String token, UpdateUserInfoBean userInfoBean, final ResponseCallBack<BaseResponse<UpdateUserInfoBean>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<UpdateUserInfoBean>> baseResponseCall = imApi.updateInfo(version,token,userInfoBean);
        baseResponseCall.enqueue(new Callback<BaseResponse<UpdateUserInfoBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<UpdateUserInfoBean>> call, Response<BaseResponse<UpdateUserInfoBean>> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UpdateUserInfoBean>> call, Throwable t) {

            }
        });
    }
    /**
     * 忘记密码
     * @param version
     * @param forgetPasswordBean 实体类
     * @param callBack
     */
    public static void forgetpwd(String version, ForgetPasswordBean forgetPasswordBean, final ResponseCallBack<BaseResponse<ForgetPasswordBean>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ForgetPasswordBean>> baseResponseCall = imApi.backpassword(version,forgetPasswordBean);
        baseResponseCall.enqueue(new Callback<BaseResponse<ForgetPasswordBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<ForgetPasswordBean>> call, Response<BaseResponse<ForgetPasswordBean>> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ForgetPasswordBean>> call, Throwable t) {

            }
        });
    }
}