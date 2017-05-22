package com.silver.chat.network;


import android.content.Context;

import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.AddFriendDiscuBody;
import com.silver.chat.network.requestbean.AskJoinGroup;
import com.silver.chat.network.requestbean.CreatGroupBean;
import com.silver.chat.network.requestbean.ExitDiscuGroupBody;
import com.silver.chat.network.requestbean.ExpelDiscuMemberBody;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.requestbean.SetDiscuMsgRemindBody;
import com.silver.chat.network.requestbean.SetGroupManagerBody;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.network.responsebean.GroupMemberBean;
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
    public static void askJoinGroup(Context context, String token, AskJoinGroup askJoinGroup, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> baseResponseCall = imApi.addGroup(token, askJoinGroup);
        BaseCallBack.enqueueBase(context,baseResponseCall,callBack);
    }

    /**
     * 获取群成员列表
     * @param context
     * @param token
     * @param groupId
     * @param callBack
     */
    public static void getGroupMem(Context context,String token,String groupId,final ResponseCallBack<BaseResponse<ArrayList<GroupMemberBean>>> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse<ArrayList<GroupMemberBean>>> groupMember = imApi.getGroupMember(token, groupId);
        BaseCallBack.enqueue(context,groupMember,callBack);
    }

    /**
     * 邀请好友加入讨论组
     * @param context
     * @param token
     * @param addFriendDiscuBean
     * @param callBack
     */
    public static void addFrDiscuGroup(Context context, String token, AddFriendDiscuBody addFriendDiscuBean, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> groupMember = imApi.addFrDiscuGroup(token, addFriendDiscuBean);
        BaseCallBack.enqueueBase(context,groupMember,callBack);
    }

    /**
     * 退出讨论组
     * @param context
     * @param token
     * @param exitDiscuGroupBody
     * @param callBack
     */
    public static void exitDiscuGroup(Context context, String token, ExitDiscuGroupBody exitDiscuGroupBody, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> groupMember = imApi.exitDiscuGroup(token, exitDiscuGroupBody);
        BaseCallBack.enqueueBase(context,groupMember,callBack);
    }

    /**
     * 踢出讨论组成员
     * @param context
     * @param token
     * @param expelDiscuMemberBody
     * @param callBack
     */
    public static void expelDiscuMember(Context context, String token, ExpelDiscuMemberBody expelDiscuMemberBody, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> groupMember = imApi.expelDiscuMember(token, expelDiscuMemberBody);
        BaseCallBack.enqueueBase(context,groupMember,callBack);
    }

    /**
     * 设置讨论组消息提醒
     * @param context
     * @param token
     * @param setDiscuMsgRemindBody
     * @param callBack
     */
    public static void setDiscuMsgRemind(Context context, String token, SetDiscuMsgRemindBody setDiscuMsgRemindBody, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> groupMember = imApi.setDiscuMsgRemind(token, setDiscuMsgRemindBody);
        BaseCallBack.enqueueBase(context,groupMember,callBack);
    }

    /**
     * 设置群管理员(群主权限)
     * @param context
     * @param token
     * @param setGroupManagerBody
     * @param callBack
     */
    public static void setGroupManager(Context context, String token, SetGroupManagerBody setGroupManagerBody, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> groupMember = imApi.setGroupManager(token, setGroupManagerBody);
        BaseCallBack.enqueueBase(context,groupMember,callBack);
    }

    /**
     * 取消群管理员(群主权限)
     * @param context
     * @param token
     * @param setGroupManagerBody
     * @param callBack
     */
    public static void deleteGroupManager(Context context, String token, SetGroupManagerBody setGroupManagerBody, final ResponseCallBack<BaseResponse> callBack) {
        ApiService imApi = RetrofitHelper.create().imApi;
        Call<BaseResponse> groupMember = imApi.deleteGroupManager(token, setGroupManagerBody);
        BaseCallBack.enqueueBase(context,groupMember,callBack);
    }



}