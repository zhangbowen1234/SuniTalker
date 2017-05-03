package com.silver.chat.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by hibon on 2016/2/26.
 * 记住用户名，密码之类的首选项
 */
public class PreferenceUtil {
    private static PreferenceUtil preference = null;
    private SharedPreferences sharedPreference;
    private String packageName = "";
    public static final String IS_FIRST = "isFirst";//第一次登陆
    public static final String ISLOG = "log";// 是否登录
    public static final String USERPHONE = "phone";
    public static final String USERID = "userId";
    public static final String USERPWD = "pwd";
    public static final String TOKEN = "token";
    public static final String IMTOKEN = "imToken"; //im令牌
    public static final String IMUSERID = "imUserId"; //im用户id
    public static final String AVATAR = "avatar";
    public static final String NICKNAME = "nickName";
    public static final String SEX = "sex";
    public static final String AGE = "age";
    public static final String SIGNATURE = "signature";
    public static final String LEVEL = "level";



    public static synchronized PreferenceUtil getInstance(Context context) {
        if (preference == null)
            preference = new PreferenceUtil(context);
        return preference;
    }

    public PreferenceUtil(Context context) {
        packageName = context.getPackageName() + "_preferences";
        sharedPreference = context.getSharedPreferences(packageName,
                Context.MODE_PRIVATE);
    }

    public void setLog(boolean isLog) {
        Editor edit = sharedPreference.edit();
        edit.putBoolean(ISLOG, isLog);
        edit.commit();
    }

    public boolean isLog() {
        return sharedPreference.getBoolean(ISLOG, false);
    }
    public void setFirst(boolean vaule) {
        Editor edit = sharedPreference.edit();
        edit.putBoolean(IS_FIRST, vaule);
        edit.commit();
    }

    public boolean isFirst() {
        return sharedPreference.getBoolean(IS_FIRST, false);
    }

    public String getString(String name, String defValue) {
        String value = sharedPreference.getString(name, defValue);
        return value;
    }

    public void setString(String name, String value) {
        Editor edit = sharedPreference.edit();
        edit.putString(name, value);
        edit.commit();
    }



    public int getInt(String name, int defValue) {
        int value = sharedPreference.getInt(name, defValue);
        return value;
    }

    public void setInt(String name, int value) {
        Editor edit = sharedPreference.edit();
        edit.putInt(name, value);
        edit.commit();
    }

    public float getFloat(String name, float defValue) {
        float value = sharedPreference.getFloat(name, defValue);
        return value;
    }

    public void setFloat(String name, float value) {
        Editor edit = sharedPreference.edit();
        edit.putFloat(name, value);
        edit.commit();
    }


}
