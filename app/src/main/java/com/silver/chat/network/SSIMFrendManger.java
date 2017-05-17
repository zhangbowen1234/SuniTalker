package com.silver.chat.network;

import android.content.Context;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.SearchIdBean;

import java.util.ArrayList;

import retrofit2.Call;

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
    public static void contactList(Context context,String version, String userId, String page, String count, String token ,
                                   final ResponseCallBack<BaseResponse<ArrayList<ContactListBean>>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ArrayList<ContactListBean>>> baseResponseCall = imApi.contactList(version,userId,page,count,token);
        BaseCallBack.enqueue(context,baseResponseCall,callBack);
    }

    /**
     * 获取全部联系人列表
     * @param version
     * @param userId 当前用户id
     * @param token
     * @param callBack
     */
    public static void allContactList(Context context,String version, String userId,String token ,
                                   final ResponseCallBack<BaseResponse<ArrayList<ContactListBean>>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ArrayList<ContactListBean>>> baseResponseCall = imApi.allContact(version,userId,token);
        BaseCallBack.enqueue(context,baseResponseCall,callBack);
    }

    /**
     * 申请添加好友
     * @param userId
     * @param friendId
     * @param comment
     * @param token
     * @param callBack
     */
    public static void goAddFriends(Context context,String userId,String friendId,String comment,String token,final ResponseCallBack<BaseResponse> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.addFriend(userId, friendId, comment, token);
        BaseCallBack.enqueueBase(context,baseResponseCall,callBack);
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
    public static void searchUser(Context context,String type,String condition,String page,String count,String token,final ResponseCallBack<BaseResponse<ArrayList<SearchIdBean>>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ArrayList<SearchIdBean>>> baseResponseCall = imApi.searchUser(type, condition, page, count, token);
        BaseCallBack.enqueue(context,baseResponseCall,callBack);
    }

    /**
     * 删除好友
     * @param token
     * @param userId
     * @param friendId
     * @param appName
     * @param callBack
     */
    public static void deleteFriend(Context context,String token,String userId,String friendId,String appName,final ResponseCallBack<BaseResponse> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.deleteFriend(token ,userId,friendId,appName);
        BaseCallBack.enqueueBase(context,baseResponseCall,callBack);
    }




}