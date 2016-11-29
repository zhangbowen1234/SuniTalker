package com.silver.chatsdk;

/**
 * 作者：Fandy on 2016/11/28 16:18
 * 邮箱：fandy618@hotmail.com
 */

public class YDClient {

    private static YDClient instance = null;

    /**
     * 单例模式
     *
     * @return 客户端对象
     */
    public synchronized static YDClient getInstance() {
        if (instance == null) {
            instance = new YDClient();
        }
        return instance;
    }

    /**
     * 注册账户
     */
    public static void creatAccount() {

    }

    /**
     * 登录账号
     */
    public static void login() {

    }
}
