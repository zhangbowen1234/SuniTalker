package com.example.factory.net;

import android.text.TextUtils;

import com.example.common.Common;
import com.example.factory.Factory;
import com.example.factory.persistence.Account;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求的封装
 * Created by bowen on 2018/3/6.
 */

public class Network {
    public static Network instance;// 单例
    private Retrofit retrofit;

    static {
        instance = new Network();
    }

    private Network() {

    }

    // 构建一个Retrofit
    public static Retrofit getRetrofit() {

        if (instance.retrofit != null) {
            return instance.retrofit;
        }
        // 得到一个OK Client
        OkHttpClient client = new OkHttpClient.Builder()
                // 给所有的请求添加一个拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // 拿到我们的请求
                        Request original = chain.request();
                        // 重新进行build
                        Request.Builder builder = original.newBuilder();
                        if (!TextUtils.isEmpty(Account.getToken())) {
                            // 全局的一个Token注入
                            builder.addHeader("token", Account.getToken());
                        }
                        builder.addHeader("Content-Type", "application/json");
                        Request newRequest = builder.build();
                        // 返回
                        return chain.proceed(newRequest);
                    }
                }).build();
        Retrofit.Builder builder = new Retrofit.Builder();
        instance.retrofit = builder.baseUrl(Common.Constance.API_URL)
                // 设置client
                .client(client)
                //设置Json解析器
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();
        //设置电脑链接
        return instance.retrofit;
    }

    /**
     * 返回一个请求代理
     *
     * @return
     */
    public static RemoteService remote() {
        return Network.getRetrofit().create(RemoteService.class);
    }
}

