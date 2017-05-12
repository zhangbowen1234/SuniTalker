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

/**
 * Created by hibon on 2017/5/8.
 * 与个人信息以及个人好友信息的网络请求
 */

public class SSIMFrendManger {


    /**
     * 分页获取联系人列表
     * @param version
     * @param userId 当前用户id
     * @param page 请求页数(从0开始)
     * @param count 每页显示条数
     * @param token
     * @param callBack
     */
    public static void contactList(String version, String userId, String page, String count,String token ,
                                   final ResponseCallBack<BaseResponse<ArrayList<ContactListBean>>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ArrayList<ContactListBean>>> baseResponseCall = imApi.contactList(version,userId,page,count,token);
        baseResponseCall.enqueue(new Callback<BaseResponse<ArrayList<ContactListBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<ArrayList<ContactListBean>>> call, Response<BaseResponse<ArrayList<ContactListBean>>> response) {
                Log.e("contactList",response.body()+"");

                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ArrayList<ContactListBean>>> call, Throwable t) {
                Log.e("aaa", t.toString() );
                callBack.onError();
            }
        });
    }

    /**
     * 获取全部联系人列表
     * @param version
     * @param userId 当前用户id
     * @param token
     * @param callBack
     */
    public static void allcontactList(String version, String userId,String token ,
                                   final ResponseCallBack<BaseResponse<ArrayList<ContactListBean>>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ArrayList<ContactListBean>>> baseResponseCall = imApi.allContactList(version,userId,token);
        baseResponseCall.enqueue(new Callback<BaseResponse<ArrayList<ContactListBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<ArrayList<ContactListBean>>> call, Response<BaseResponse<ArrayList<ContactListBean>>> response) {
                Log.e("contactList",response.body()+"");

                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ArrayList<ContactListBean>>> call, Throwable t) {
                Log.e("aaa", t.toString() );
                callBack.onError();
            }
        });
    }


    /**
     * 申请添加好友
     * @param userId
     * @param friendId
     * @param comment
     * @param token
     * @param callBack
     */
    public static void goAddFriends(String userId,String friendId,String comment,String token,final ResponseCallBack<BaseResponse> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.addFriend(userId, friendId, comment, token);
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Log.e("addFriends",response.toString()+"");
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());

                }else{
                    callBack.onFailed(response.body());
                }
                Log.e("response", response.body().toString());

            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onError();
            }
        });
    }

    /**
     * 搜索用户
     * @param type
     * @param condition
     * @param page
     * @param count
     * @param token
     * @param callBack
     */
    public static void searchUser(String type,String condition,String page,String count,String token,final ResponseCallBack<BaseResponse<ArrayList<SearchIdBean>>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ArrayList<SearchIdBean>>> baseResponseCall = imApi.searchUser(type, condition, page,count, token);
        baseResponseCall.enqueue(new Callback<BaseResponse<ArrayList<SearchIdBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<ArrayList<SearchIdBean>>> call, Response<BaseResponse<ArrayList<SearchIdBean>>> response) {
                Log.e("searchUser",response.body()+"");
                if (response.body().getStatusCode() == 200){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ArrayList<SearchIdBean>>> call, Throwable t) {
                callBack.onError();
            }
        });
    }

}