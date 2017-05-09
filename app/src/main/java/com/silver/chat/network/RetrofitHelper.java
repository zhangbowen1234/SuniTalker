package com.silver.chat.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joe on 2017/4/26.
 */

public class RetrofitHelper {
    private static RetrofitHelper mInstance = new RetrofitHelper();
    Retrofit retrofit;
    ApiService imApi;

    private RetrofitHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        imApi = retrofit.create(ApiService.class);
    }

    public static RetrofitHelper create() {
        return mInstance;
    }

    private ApiService getImApi() {
        return imApi;
    }
}
