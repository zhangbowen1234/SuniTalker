package com.silver.chat;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.silver.chatsdk.SSIMClient;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;

/**
 * 作者：Fandy on 2016/11/22 14:21
 * 邮箱：fandy618@hotmail.com
 */

public class Appcontext extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Glide.get(this)
                .register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient()));
        sContext = this;
        SSIMClient.getInstance().init(sContext);
    }
}
