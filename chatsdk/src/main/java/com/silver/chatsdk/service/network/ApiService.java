package com.silver.chatsdk.service.network;

import com.silver.chatsdk.service.bean.Person;
import com.silver.chatsdk.service.bean.RegisterRequest;
import com.silver.chatsdk.service.bean.RegisterResponse;
import com.silver.chatsdk.service.bean.RequestInfo;
import com.silver.chatsdk.service.bean.SigninRequest;
import com.silver.chatsdk.service.bean.SigninResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：Fandy on 2016/12/2 10:34
 * 邮箱：fandy618@hotmail.com
 */
public interface APIService {

    @GET("/")
    Call<Person> getOnePersonInfo(@Query("app") String app, @Query("appkey") String appkey, @Query("sign") String sign, @Query("format") String format, @Query("idcard") String idcard);

    @POST("um4cli")
    Call<Person> regist(@Body RequestInfo info);

    @POST("um4cli")
    Call<RegisterResponse> register(@Body RegisterRequest info);

    @POST("um4cli")
    Call<SigninResponse> signin(@Body SigninRequest info);
}
