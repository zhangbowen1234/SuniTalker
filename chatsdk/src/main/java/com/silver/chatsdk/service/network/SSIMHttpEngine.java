package com.silver.chatsdk.service.network;

import com.silver.chatsdk.service.manager.SSIMEngine;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SSIMHttpEngine extends SSIMNetworkEngine{

    private APIService httpEngine;


    private SSIMHttpEngine() {
    }

    private static class SingletonHolder {
        private static final SSIMHttpEngine INSTANCE = new SSIMHttpEngine();  //创建实例的地方
    }

    public static SSIMHttpEngine getInstance() {
        SSIMHttpEngine httpEngine = SingletonHolder.INSTANCE;
        synchronized (APIService.class) {
            if (httpEngine.httpEngine == null) {
                SSIMEngine engine = SSIMEngine.getInstance();

                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(engine.getConfig().getHttpConfig().formatURL())
                        .baseUrl("http://imx.users.docker.sspaas.net/")
                        .baseUrl("http://pan.sspaas.com/sspaas-cloud/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(new OkHttpClient.Builder()
                                .addInterceptor(new Interceptor() {
                                    @Override
                                    public Response intercept(Chain chain) throws IOException {
                                        Request request = chain.request()
                                                .newBuilder()
//                                        .addHeader("a", "b")
                                                .build();
                                        return chain.proceed(request);
                                    }
                                })
                                .connectTimeout(15, TimeUnit.SECONDS)
                                .readTimeout(20, TimeUnit.SECONDS)
                                .writeTimeout(20, TimeUnit.SECONDS)
                                .build())
                        .build();
                httpEngine.httpEngine = retrofit.create(APIService.class);
            }
        }

        return httpEngine;
    }

    @Override
    public  APIService getEngine() {
        return this.httpEngine;
    }
}
