package com.silver.chat.network;

import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.LoginRequest;
import com.silver.chat.network.responsebean.LoginRequestBean;
import com.silver.chat.network.responsebean.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by joe on 2017/4/26.
 * 定义当前项目的所有业务逻辑的请求方法
 */

public interface ApiService {

    //验证手机是否注册
    @GET("imx/{version}/user/account/{mobile}")
    Call<BaseResponse> checkPhoneCode(@Path("version") String version, @Path("mobile") String phone);

    //获取验证码
    @GET("imx/{version}/sms/{phone}/{genre}")
    Call<BaseResponse> registCode(@Path("version") String version, @Path("phone") String phone, @Path("genre") int genre);

    //注册
    @POST("imx/{version}/user/register")
    Call<BaseResponse> registPhone(@Path("version") String version, @Body RegisterRequest registerRequest);

    //登录
    @POST("imx/{version}/user/login")
    Call<BaseResponse<LoginRequestBean>> goLogin(@Path("version") String version, @Body LoginRequest loginRequest);

}
