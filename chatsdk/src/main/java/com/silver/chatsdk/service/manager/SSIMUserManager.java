package com.silver.chatsdk.service.manager;

import android.content.Context;

import com.silver.chatsdk.service.bean.RegisterRequest;
import com.silver.chatsdk.service.bean.RegisterResponse;
import com.silver.chatsdk.service.bean.SigninRequest;
import com.silver.chatsdk.service.bean.SigninResponse;
import com.silver.chatsdk.service.network.APIService;
import com.silver.chatsdk.service.bean.ResponseCallBackInterface;
import com.silver.chatsdk.service.network.SSIMHttpEngine;
import com.silver.chatsdk.service.network.SSIMHttpsEngine;
import com.silver.chatsdk.service.network.SSIMNetworkEngine;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * http 用户相关请求处理管理类
 */
public class SSIMUserManager {
    private Context ctx;

    SSIMUserManager(Context ctx){
        this.ctx = ctx;
    }



    /**
     * 用户注册
     * 密码需要经过md5加密
     * @param callBack 回调对象
     * @param request  请求对象
     */
    public void ssimRegister(final ResponseCallBackInterface<RegisterResponse> callBack, RegisterRequest request){

        SSIMNetworkEngine networkEngine = null;
        if (SSIMEngine.getInstance().isMutualAuth()){
            networkEngine = SSIMHttpsEngine.getInstance(ctx);
        }else{
            networkEngine = SSIMHttpEngine.getInstance();
        }
//        对密码进行md5加密
//        request.setPwd(MD5Util.MD5(request.getPwd()));
        Call<RegisterResponse> call = ((APIService)networkEngine.getEngine()).register(request);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.body().getCode() == 1){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body().getCode());
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callBack.onError();
            }
        });

    }

    /**
     * 用户登录
     * 密码需要经过md5加密
     * @param callBack 回调对象
     * @param request 请求对象
     */
    public void ssimSignin(final ResponseCallBackInterface<SigninResponse> callBack, SigninRequest request){

        SSIMNetworkEngine networkEngine = null;
        if (SSIMEngine.getInstance().isMutualAuth()){
            networkEngine = SSIMHttpsEngine.getInstance(ctx);
        }else{
            networkEngine = SSIMHttpEngine.getInstance();
        }

//        对密码进行md5加密
//        request.setPwd(MD5Util.MD5(request.getPwd()));
        Call<SigninResponse> call = ((APIService)networkEngine.getEngine()).signin(request);


        call.enqueue(new Callback<SigninResponse>() {
            @Override
            public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                if (response.body().getCode() == 1){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body().getCode());
                }

            }

            @Override
            public void onFailure(Call<SigninResponse> call, Throwable t) {
                callBack.onError();
            }
        });
    }

}
