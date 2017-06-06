package com.silver.chat;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.lqr.emoji.IImageLoader;
import com.lqr.emoji.LQREmotionKit;
import com.silver.chat.base.Common;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.Utils;
import com.squareup.okhttp.OkHttpClient;
import com.ssim.android.callback.HttpResultCallback;
import com.ssim.android.engine.SSEngine;
import com.ssim.android.http.bean.VerifyAppKeyAndSecretResponse;
import com.ssim.android.listener.SSMessageReceiveListener;
import com.ssim.android.model.chat.SSMessage;
import com.ssim.android.model.chat.SSP2PMessage;

import java.io.InputStream;
import java.util.UUID;

/**
 * 作者：Fandy on 2016/11/22 14:21
 * 邮箱：fandy618@hotmail.com
 */

public class AppContext extends MultiDexApplication implements SSMessageReceiveListener {

    public static AppContext appContext;
    public SSMessage mSSmessage;
    public SSEngine instance;

    public AppContext() {
    }

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
        //获取设备 UUID 号码
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        PreferenceUtil.getInstance(this).setString(PreferenceUtil.UUIQUEID, uniqueId);
        /**
         *  SDK调用第一步 初始化
         *
         */
        SSEngine.registerApp(Common.APPKEY, Common.APPSECRET, uniqueId,
                appContext, new HttpResultCallback<VerifyAppKeyAndSecretResponse>() {
                    @Override
                    public void onSuccess(VerifyAppKeyAndSecretResponse verifyAppKeyAndSecretResponse) {
                        /**
                         * verifyAppKeyAndSecretResponse.getCode 为1表示初始化成功，否则失败
                         */
                        Log.e("SSEngine.registerApp_onSuccess", verifyAppKeyAndSecretResponse.getCode() + "");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Log.e("SSEngine.registerApp_onFailure", i + "/" + s);
                    }

                    @Override
                    public void onError(String s) {
                        Log.e("SSEngine.registerApp_onError", s);
                    }
                });

        /**
         * 接收群和个人消息及通知监听
         */
        instance = SSEngine.getInstance();
        instance.setMsgRcvListener(this);

    }

    public static AppContext getInstance() {
        return appContext;
    }

    @Override
    public void receiveMsg(SSMessage ssMessage) {
        if (ssMessage instanceof SSP2PMessage) {
            SSP2PMessage receiveMsg = (SSP2PMessage) ssMessage;
            String sourceId = receiveMsg.getSourceId();
            Log.e("appContext_receiveMsg", sourceId + ":" + receiveMsg.getContent());
        }
    }
}
