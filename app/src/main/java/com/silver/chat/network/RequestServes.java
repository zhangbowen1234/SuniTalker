package com.silver.chat.network;

import com.silver.chatsdk.config.SSIMConfig;
import com.silver.chatsdk.service.bean.Person;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hibon on 2017/4/26.
 */

public interface RequestServes {

    @GET("imx/{version}/sms/{phone}/{genre}")
    Call<String> getContact(@Path("version") String leaf, @Path("phone") String phone, @Path("genre") int genre);


}
