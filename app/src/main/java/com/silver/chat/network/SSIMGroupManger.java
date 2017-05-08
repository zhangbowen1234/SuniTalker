package com.silver.chat.network;

import com.silver.chat.base.Common;
import com.silver.chat.entity.GroupBean;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.responsebean.BaseResponse;

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
    public static void getJoinGroupList(String version, JoinedGroupRequest request, final ResponseCallBack<BaseResponse<GroupBean>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<GroupBean>> baseResponseCall = imApi.joinedGroupList(Common.version,request);
        baseResponseCall.enqueue(new Callback<BaseResponse<GroupBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<GroupBean>> call, Response<BaseResponse<GroupBean>> response) {
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<GroupBean>> call, Throwable t) {
                callBack.onError();
            }
        });
    }
}
