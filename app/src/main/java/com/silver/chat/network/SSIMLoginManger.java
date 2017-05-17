package com.silver.chat.network;

import android.content.Context;

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

/**
 * Created by hibon on 2017/4/26.
 * 登录注册请求
 */

public class SSIMLoginManger {

    /**
     * 验证手机号是否注册
     *
     * @param version  版本号当前为leaf
     * @param phone    手机号
     * @param callBack
     */
    public static void checkPhone(Context context,String version, String phone, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.checkPhoneCode(version, phone);
        BaseCallBack.enqueueBase(context,baseResponseCall, callBack);
    }

    /**
     * 发送验证码
     *
     * @param version  版本号当前为leaf
     * @param phone    手机号
     * @param genre    请求种类
     * @param callBack 请求回调
     */
    public static void userReginstCode(Context context,String version, String phone, int genre, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.registCode(version, phone, genre);
        BaseCallBack.enqueueBase(context,baseResponseCall, callBack);
    }

    /**
     * 注册
     *
     * @param version
     * @param request  注册对象
     * @param callBack
     */
    public static void goReginst(Context context,String version, RegisterRequest request, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.registPhone(version, request);
        BaseCallBack.enqueueBase(context,baseResponseCall, callBack);
    }

    /**
     * 登录
     *
     * @param version
     * @param loginRequest 登录对象
     * @param callBack
     */
    public static void goLogin(Context context,String version, LoginRequest loginRequest, final ResponseCallBack<BaseResponse<LoginRequestBean>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<LoginRequestBean>> baseResponseCall = imApi.goLogin(version, loginRequest);
        BaseCallBack.enqueue(context,baseResponseCall,callBack);

    }


    /**
     * 获取用户信息
     *
     * @param version
     * @param token
     * @param callBack
     */
    public static void getUserInfo(Context context,String version, String token, final ResponseCallBack<BaseResponse<UserInfoBean>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<UserInfoBean>> baseResponseCall = imApi.userInfo(version, token);
        BaseCallBack.enqueue(context,baseResponseCall,callBack);
    }

    /**
     * 获取好友信息
     *
     * @param version
     * @param userId   当前用户id
     * @param friendid 好友id
     * @param token
     * @param callBack
     */
    public static void friendinfo(Context context,String token, String version, String userId, String friendid,
                                  final ResponseCallBack<BaseResponse<List<FriendInfo>>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<List<FriendInfo>>> baseResponseCall = imApi.friendinfo(token, version, userId, friendid);
        BaseCallBack.enqueue(context,baseResponseCall,callBack);
    }

    /**
     * 退出登录
     *
     * @param version
     * @param token
     * @param callBack
     */
    public static void outLogin(Context context,String version, String token, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.outLogin(version, token);
        BaseCallBack.enqueueBase(context,baseResponseCall, callBack);

    }

    /**
     * 修改好友信息
     *
     * @param version
     * @param userInfoBean 实体类
     * @param token
     * @param callBack
     */
    public static void updateUserInfo(Context context,String version, String token, UpdateUserInfoBean userInfoBean, final ResponseCallBack<BaseResponse<UpdateUserInfoBean>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<UpdateUserInfoBean>> baseResponseCall = imApi.updateInfo(version, token, userInfoBean);
        BaseCallBack.enqueue(context,baseResponseCall, callBack);

    }

    /**
     * 忘记密码
     *
     * @param version
     * @param forgetPasswordBean 实体类
     * @param callBack
     */
    public static void forgetpwd(Context context,String version, ForgetPasswordBean forgetPasswordBean, final ResponseCallBack<BaseResponse<ForgetPasswordBean>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ForgetPasswordBean>> baseResponseCall = imApi.backpassword(version, forgetPasswordBean);
        BaseCallBack.enqueue(context,baseResponseCall, callBack);

    }

//    public static void upLoadHead(String token, MultipartBody.Part file ,
//                                  final ResponseCallBack<BaseResponse> callBack){
//        ApiService imApi = RetrofitHelperUpLoadHead.create().imApi;
//        Call<BaseResponse> baseResponseCall = imApi.upLoadHead(token,file);
//        BaseCallBack.enqueueBase(baseResponseCall,callBack);
//    }

}
