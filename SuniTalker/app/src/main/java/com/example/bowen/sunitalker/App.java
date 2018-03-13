package com.example.bowen.sunitalker;

import com.example.common.comm.app.Application;
import com.example.factory.Factory;
import com.igexin.sdk.PushManager;

/**
 * 项目初始化
 * Created by bowen on 2018/2/27.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 调用Factory进行初始化
        Factory.setup();
        // 推送进行初始化
        PushManager.getInstance().initialize(this);
    }
}
