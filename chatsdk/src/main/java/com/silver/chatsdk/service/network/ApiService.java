package com.silver.chatsdk.service.network;

import com.silver.chatsdk.service.bean.Person;
import com.silver.chatsdk.service.bean.RequestInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：Fandy on 2016/12/2 10:34
 * 邮箱：fandy618@hotmail.com
 */
public interface ApiService {

    @GET("/")
    Call<Person> getOnePersonInfo(@Query("app") String app, @Query("appkey") String appkey, @Query("sign") String sign, @Query("format") String format, @Query("idcard") String idcard);

    @POST("um4cli")
    Call<Person> regist(@Body RequestInfo info);
}
