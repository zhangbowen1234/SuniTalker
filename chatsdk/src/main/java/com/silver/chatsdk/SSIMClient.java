package com.silver.chatsdk;

import android.content.Context;
import android.util.Log;

import com.silver.chatsdk.service.bean.BaseBean;
import com.silver.chatsdk.service.bean.Person;
import com.silver.chatsdk.service.bean.RequestInfo;
import com.silver.chatsdk.service.network.ApiService;
import com.silver.chatsdk.service.network.SSIMHttpEngine;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Fandy on 2016/11/28 16:18
 * 邮箱：fandy618@hotmail.com
 */

public class SSIMClient {

    private static SSIMClient instance = null;
    private Context mContext;

    /**
     * 单例模式
     *
     * @return 客户端对象
     */
    public synchronized static SSIMClient getInstance() {
        if (instance == null) {
            instance = new SSIMClient();
        }
        return instance;
    }

    public void init(Context context) {
        mContext = context;
    }

    /**
     * 注册账户
     */
    public void creatAccount(final ResponseCallBack callBack) {
        ApiService service = SSIMHttpEngine.getInstance(mContext);
        Call<Person> regist = service.regist(new RequestInfo("REGISTER", "1380000000", "123", "123"));
        regist.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.e("onResponse", response.body().toString());
                callBack.onSuccess(new BaseBean(1, "123"));
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("onFailure", "onFailure");
                callBack.onFailed(-1);
            }
        });
    }

    /**
     * 登录账号
     */
    public void login() {

    }
}
