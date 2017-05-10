package com.silver.chat;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.lqr.emoji.IImageLoader;
import com.lqr.emoji.LQREmotionKit;
import com.silver.chat.util.Utils;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;

import static android.R.attr.path;

/**
 * 作者：Fandy on 2016/11/22 14:21
 * 邮箱：fandy618@hotmail.com
 */

public class AppContext extends Application {

    public static AppContext appContext;
    public AppContext(){}

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        Utils.init(appContext);
        Glide.get(this)
                .register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient()));
        appContext = this;
        //SSIMClient.getInstance().init(sContext);
        //初始化表情控件
        LQREmotionKit.init(this, new IImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            }
        });
    }
}
