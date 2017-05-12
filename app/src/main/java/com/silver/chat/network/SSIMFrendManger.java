package com.silver.chat.network;

import android.util.Log;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.SearchIdBean;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by hibon on 2017/5/8.
 * 与个人信息以及个人好友信息的网络请求
 */

public class SSIMFrendManger {



    /**
     * 获取联系人列表
     *
     * @param version
     * @param userId   当前用户id
     * @param page     请求页数(从0开始)
     * @param count    每页显示条数
     * @param token
     * @param callBack
     */
    public static void contactList(String version, String userId, String page, String count, String token,
                                   final ResponseCallBack<BaseResponse<ArrayList<ContactListBean>>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ArrayList<ContactListBean>>> baseResponseCall = imApi.contactList(version, userId, page, count, token);
        enqueue(baseResponseCall,callBack);
    }


    /**
     * 申请添加好友
     *
     * @param userId
     * @param friendId
     * @param comment
     * @param token
     * @param callBack
     */
    public static void goAddFriends(String userId, String friendId, String comment, String token, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.addFriend(userId, friendId, comment, token);
        enqueueBase(baseResponseCall,callBack);

    }

    /**
     * 搜素用户
     *
     * @param type
     * @param condition
     * @param page
     * @param count
     * @param token
     * @param callBack
     */
    public static void searchUser(String type, String condition, String page, String count, String token, final ResponseCallBack<BaseResponse<ArrayList<SearchIdBean>>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ArrayList<SearchIdBean>>> baseResponseCall = imApi.searchUser(type, condition, page, count, token);
        enqueue(baseResponseCall,callBack);

    }



    /**
     * 后台返回的数据没有data调用这个
     */
    public static <T>void enqueue(Call<BaseResponse<T>> baseResponseCall, final ResponseCallBack<BaseResponse<T>> callBack) {
        baseResponseCall.enqueue(new Callback<BaseResponse<T>>() {
            @Override
            public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
                Log.e("contactList", response.body() + "");

                if (response.body().getStatusCode() == 200) {
                    callBack.onSuccess(response.body());
                } else if(response.body().getStatusCode() == 300){








                }else {
                    callBack.onFailed(response.body());

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
                Log.e("aaa", t.toString());
                callBack.onError();
            }
        });
    }
    public static void enqueueBase(Call<BaseResponse> baseResponseCall, final ResponseCallBack<BaseResponse> callBack) {
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                if (response.body().getStatusCode() == 200) {
                    callBack.onSuccess(response.body());
                } else if(response.body().getStatusCode() == 300){


                }else {
                    callBack.onFailed(response.body());

                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("aaa", t.toString());
                callBack.onError();
            }
        });
    }
}