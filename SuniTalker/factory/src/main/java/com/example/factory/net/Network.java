package com.example.factory.net;

import com.example.common.Common;
import com.example.factory.Factory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求的封装
 * Created by bowen on 2018/3/6.
 */

public class Network {

    // 构建一个Retrofit
    public static Retrofit getRetrofit() {

        // 得到一个OKClient
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit.Builder builder = new Retrofit.Builder();
        //设置电脑链接
        return builder.baseUrl(Common.Constance.API_URL)
                // 设置client
                .client(client)
                //设置Json解析器
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();
    }
}

