package com.silver.chat.network;

import android.util.Log;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by joe on 2017/4/26.
 */

public class SSIMUserMange {
    public static void userReginstCode(final ResponseCallBack<BaseResponse> callBack, String phone) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.registCode(phone);
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
                //Log.e("RegisterPhoneActivity", response.body().toString() );
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
            }
        });
    }
}
