package com.example.factory.presistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.factory.Factory;
import com.example.factory.modle.api.account.AccountRspModel;
import com.example.factory.modle.db.User;
import com.example.factory.modle.db.User_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * 持久化值
 * Created by bowen on 2018/3/13.
 */

public class Account {
    private static final String KEY_PUSH_ID = "KEY_PUSH_ID";
    private static final String KEY_IS_BIND = "KEY_IS_BIND";
    private static final String KEY_USERTOKEN= "KEY_USERTOKEN";
    private static final String KEY_USERID = "KEY_USERID";
    private static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    // 设备的推送Id
    private static String pushId;
    // 设备Id是否已经绑定到了服务器
    private static boolean isBind;
    // 登录状态的Token，用来接口请求
    private static String token;
    // 登录的用户ID
    private static String userId;
    // 登录的账户
    private static String account;

    /**
     * 存储数据到XML文件，持久化
     */
    private static void save(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        sp.edit()
                .putString(KEY_PUSH_ID, pushId)
                .putBoolean(KEY_IS_BIND,isBind)
                .apply();
    }

    /**
     * 进行数据加载
     */
    public static void load(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        pushId = sp.getString(KEY_PUSH_ID, "");
        isBind = sp.getBoolean(KEY_PUSH_ID, false);
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
        // 用户Id 和 Token 不为空
        return !TextUtils.isEmpty(userId)
                &&!TextUtils.isEmpty(token);
    }

    /**
     * 是否已经完事了用户信息
     * @return True 是完成了
     */
    public static boolean isComplete(){
        // TODO
        return isLogin();
    }
    /**
     * 是否已经绑定到了服务器
     *
     * @return True已经绑定了
     */
    public static boolean isBind() {
        return isBind;
    }

    /**
     * 设置绑定状态
     */
    public static void setBind(boolean isBind){
        Account.isBind=isBind;
        Account.save(Factory.app());
    }

    /**
     * 保存我自己的信息到持久化XML
     * @param model AccountRspModel
     */
    public static void login(AccountRspModel model){
        // 存储当前登录的账户，token，用户iD,方便从数据库中查询我的信息
        Account.token = model.getToken();
        Account.account = model.getAccount();
        Account.userId = model.getUser().getId();
        save(Factory.app());
    }

    /**
     * 获取当前登录的用户信息
     * @return
     */
    public static User getUser(){
        // 如果为null返回一个new的User，其次从数据库查询
        return TextUtils.isDigitsOnly(userId)?new User(): SQLite.select()
                .from(User.class)
                .where(User_Table.id.eq(userId))
                .querySingle(); // 查询一个
    }
}
