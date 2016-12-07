package com.silver.chatsdk.service.manager;

import android.content.Context;

import com.silver.chatsdk.config.SSIMConfig;


/**
 * SSPAAS IM
 */
public class SSIMEngine {

    private SSIMConfig config;

    public SSIMConfig getConfig() {
        return config;
    }

    public void setConfig(SSIMConfig config) {
        this.config = config;
    }

    //单例
    private static class SingletonHolder {
        private static final SSIMEngine INSTANCE = new SSIMEngine();
    }

    private SSIMEngine() {
        this.config = new SSIMConfig();
    }
    /**
     *获取单例对象
     * 如果为第一次初始化，获取到的对象初始化为所传参数
     * @return SSIMEngine单例对象
     */
    public static SSIMEngine getInstance(SSIMConfig cfg) {
        SSIMEngine instance = SingletonHolder.INSTANCE;
        instance.init(cfg);
        return instance;
    }

    /**
     *获取单例对象
     * 如果是第一次初始化，获取到的对象初始化为默认参数
     * @return SSIMEngine单例对象
     */
    public static SSIMEngine getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private void init(SSIMConfig cfg) {
        this.config = cfg;
    }

    public SSIMUserManager ssimGetUserManager(Context ctx) {
        return new SSIMUserManager(ctx);
    }

    /**
     * HTTPS请求是否需要双向验证
     * @return boolean
     */
    public boolean isMutualAuth(){
        return this.config.getHttpConfig().isMutualAuth();
    }
}
