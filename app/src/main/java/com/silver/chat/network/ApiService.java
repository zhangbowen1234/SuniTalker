package com.silver.chat.network;

import com.silver.chat.network.responsebean.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by joe on 2017/4/26.
 * 定义当前项目的所有业务逻辑的请求方法
 */

public interface ApiService {


    //获取验证码
    @GET("imx/leaf/sms/{phone}/1")
    Call<BaseResponse> registCode(@Path("phone") String phone);
}
