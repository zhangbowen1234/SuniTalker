package com.silver.chat.network;

import android.content.Context;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.AgreeFriendAddBody;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.QueryUserInfoBean;
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
     * 一次性获取全部联系人列表
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

    /**
     * 修改好友备注
     * @param context
     * @param token
     * @param userId
     * @param friendId
     * @param note 备注名
     * @param callBack
     */
    public static void revampFriendName(Context context,String token,String userId,String friendId,String note,final ResponseCallBack<BaseResponse> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.revampFriendName(token ,userId,friendId,note);
        BaseCallBack.enqueueBase(context,baseResponseCall,callBack);
    }

    /**
     * 屏蔽好友
     * @param context
     * @param token
     * @param userId
     * @param friendId
     * @param callBack
     */
    public static void shieldFriend(Context context,String token,String userId,String friendId,final ResponseCallBack<BaseResponse> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.shieldFriend(token ,userId,friendId);
        BaseCallBack.enqueueBase(context,baseResponseCall,callBack);
    }

    /**
     * 拒绝好友申请
     * @param context
     * @param token
     * @param userId
     * @param friendId
     * @param callBack
     */
    public static void repulseFriend(Context context,String token,String userId,String friendId,final ResponseCallBack<BaseResponse> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.repulseFriendAdd(token ,userId,friendId);
        BaseCallBack.enqueueBase(context,baseResponseCall,callBack);
    }

    /**
     * 通过好友申请
     * @param context
     * @param token
     * @param agreeFriendAddBody
     * @param callBack
     */
    public static void agreeFriend(Context context, String token, AgreeFriendAddBody agreeFriendAddBody, final ResponseCallBack<BaseResponse> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.agreeFriend(token ,agreeFriendAddBody);
        BaseCallBack.enqueueBase(context,baseResponseCall,callBack);
    }

    /**
     *通过用户Id查询用户信息
     * @param context
     * @param token
     * @param userId
     * @param callBack
     */
    public static void idQueryUserInfo(Context context, String token, String userId, final ResponseCallBack<BaseResponse<QueryUserInfoBean>> callBack){
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<QueryUserInfoBean>> baseResponseCall = imApi.idQueryUserInfo(token ,userId);
        BaseCallBack.enqueue(context,baseResponseCall,callBack);
    }



}