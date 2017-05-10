package com.silver.chat.network;

import android.util.Log;

import com.silver.chat.base.Common;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.CreatGroupBean;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupBean;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Joe on 2017/5/8.
 * 与群组相关的请求
 */

public class SSIMGroupManger {
    /**
     * 获取用户加入的群组的post请求
     * @param version
     * @param request
     * @param callBack
     */
    public static void getJoinGroupList(String version, final JoinedGroupRequest request, String token, final ResponseCallBack<BaseResponse<ArrayList<GroupBean>>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        final Call<BaseResponse<ArrayList<GroupBean>>> baseResponseCall = imApi.joinedGroupList(version, request, token);
        imApi.joinedGroupList(version, request, token);
        baseResponseCall.enqueue(new Callback<BaseResponse<ArrayList<GroupBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<ArrayList<GroupBean>>> call, Response<BaseResponse<ArrayList<GroupBean>>> response) {
                //请求成功返回的状态码在此处是1
                if (response.body().getStatusCode() == 1) {
                    callBack.onSuccess(response.body());
                    Log.e("GroupChatActivity", response.body().toString() );
                } else {
                    callBack.onFailed(response.body());
                    Log.e("GroupChatActivity", response.body().toString() );

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ArrayList<GroupBean>>> call, Throwable t) {
                Log.e("GroupChatActivity", t.toString() );
            }
        });
    }    /**
     * 创建群组的post请求
     * @param version
     * @param token
     * @param callBack
     */
    public static void Getcreatgroup(String version,  String token, CreatGroupBean creatGroupBean, final ResponseCallBack<BaseResponse<CreatGroupBean>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<CreatGroupBean>> baseResponseCall = imApi.creatgroup(version,token ,creatGroupBean);
        baseResponseCall.enqueue(new Callback<BaseResponse<CreatGroupBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<CreatGroupBean>> call, Response<BaseResponse<CreatGroupBean>> response) {
                if (response.body().getStatusCode() == 200) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailed(response.body());
                }
                Log.e("response", response.body().toString());
            }

            @Override
            public void onFailure(Call<BaseResponse<CreatGroupBean>> call, Throwable t) {
                callBack.onError();
                Log.e("response", t.toString());
            }
        });
    }
    /**
     * 创建讨论组的post请求
     * @param version
     * @param token
     * @param callBack
     */
    public static void Getcreatdicugroup(String version,  String token, CreatGroupBean creatGroupBean, final ResponseCallBack<BaseResponse<CreatGroupBean>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<CreatGroupBean>> baseResponseCall = imApi.creatdiscugroup(version,token ,creatGroupBean);
        baseResponseCall.enqueue(new Callback<BaseResponse<CreatGroupBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<CreatGroupBean>> call, Response<BaseResponse<CreatGroupBean>> response) {
                if (response.body().getStatusCode() == 200) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailed(response.body());
                }
                Log.e("response", response.body().toString());
            }

            @Override
            public void onFailure(Call<BaseResponse<CreatGroupBean>> call, Throwable t) {
                callBack.onError();
                Log.e("response", t.toString());
            }
        });
    }
}
