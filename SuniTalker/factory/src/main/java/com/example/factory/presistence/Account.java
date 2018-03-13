package com.example.factory.presistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.factory.Factory;

/**
 * 持久化值
 * Created by bowen on 2018/3/13.
 */

public class Account {
    private static final String KEY_PUSH_ID = "KEY_PUSH_ID";
    // 设备的推送Id
    private static String pushId;

    /**
     * 存储数据到XML文件，持久化
     */
    private static void save(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        sp.edit()
                .putString(KEY_PUSH_ID, pushId)
                .apply();
    }

    /**
     * 进行数据加载
     */
    public static void load(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        pushId = sp.getString(KEY_PUSH_ID, "");
    }

    /**
     * 设置并存储设备的Id
     *
     * @param pushId 设备的推送Id
     */
    public static void setPushId(String pushId) {
        Account.pushId = pushId;
        Account.save(Factory.app());
    }

    /**
     * 获取推送Id
     *
     * @return 推送Id
     */
    public static String getPushId() {
        return pushId;
    }

    /**
     * 返回当前账号是否登录
     *
     * @return True已登录
     */
    public static boolean isLogin() {
        return true;
    }

    /**
     * 是否已经绑定到了服务器
     *
     * @return True已经绑定了
     */
    public static boolean isBind() {
        return false;
    }
}
