package com.silver.chat.network;


import android.content.Context;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.AskJoinGroup;
import com.silver.chat.network.requestbean.CreatGroupBean;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.network.responsebean.SearchGroupBean;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by Joe on 2017/5/8.
 * 与群组相关的请求
 */

public class SSIMGroupManger {
    /**
     * 获取用户加入的群组的post请求
     *
     * @param version
     * @param request
     * @param callBack
     */
    public static void getJoinGroupList(Context context,String version, final JoinedGroupRequest request, String token, final ResponseCallBack<BaseResponse<ArrayList<GroupBean>>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        final Call<BaseResponse<ArrayList<GroupBean>>> baseResponseCall = imApi.joinedGroupList(version, request, token);
        imApi.joinedGroupList(version, request, token);
        BaseCallBack.enqueue(context,baseResponseCall,callBack);
    }

    /**
     * 创建群组的post请求
     *
     * @param version
     * @param token
     * @param callBack
     */
    public static void getcreatgroup(Context context,String version, String token, CreatGroupBean creatGroupBean, final ResponseCallBack<BaseResponse<CreatGroupBean>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<CreatGroupBean>> baseResponseCall = imApi.creatgroup(version, token, creatGroupBean);
        BaseCallBack.enqueue(context,baseResponseCall, callBack);
    }

    /**
     * 创建讨论组的post请求
     *
     * @param version
     * @param token
     * @param callBack
     */
    public static void getcreatdicugroup(Context context,String version, String token, CreatGroupBean creatGroupBean, final ResponseCallBack<BaseResponse<CreatGroupBean>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<CreatGroupBean>> baseResponseCall = imApi.creatdiscugroup(version, token, creatGroupBean);
        BaseCallBack.enqueue(context,baseResponseCall, callBack);
    }

    /**
     * 获取搜索群组信息
     * @param token
     * @param condition
     * @param page
     * @param count
     */
    public static void getSearchGroupInfo(Context context,String token,String condition,String page,String count, final ResponseCallBack<BaseResponse<SearchGroupBean>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<SearchGroupBean>> baseResponseCall = imApi.searchGroup(token, condition, page, count);
        BaseCallBack.enqueue(context,baseResponseCall,callBack);

    }

    /**
     * 申请加群
     * @param token
     * @param askJoinGroup
     * @param callBack
     */
    public static void askJionGroup(Context context,String token, AskJoinGroup askJoinGroup,final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.addGroup(token, askJoinGroup);
        BaseCallBack.enqueueBase(context,baseResponseCall,callBack);

    }



}