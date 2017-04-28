package com.silver.chat.network;

import android.content.Context;
import android.util.Log;

import com.silver.chatsdk.service.bean.BaseResponse;
import com.silver.chatsdk.service.bean.ResponseCallBackInterface;
import com.silver.chatsdk.service.network.APIService;
import com.silver.chatsdk.service.network.SSIMHttpEngine;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Fandy on 2016/11/28 16:18
 * 邮箱：fandy618@hotmail.com
 */

public class SSIMClient {

    private static SSIMClient instance ;
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
    public void creatAccount(String phone,int smsType,final ResponseCallBackInterface callBack) {
        SSIMHttpEngine instance = SSIMHttpEngine.getInstance();
        APIService engine = instance.getEngine();
        Call<BaseResponse> regist = engine.getContact("leaf",phone,1);
        regist.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Log.e("onResponse1", response.body().toString());
                callBack.onSuccess(response.body().getErrMsg());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
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
