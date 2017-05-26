package com.silver.chat.network;

import com.silver.chat.network.requestbean.AddFriendDiscuBody;
import com.silver.chat.network.requestbean.AgreeFriendAddBody;
import com.silver.chat.network.requestbean.AskJoinGroup;
import com.silver.chat.network.requestbean.CreatGroupBean;
import com.silver.chat.network.requestbean.ExitDiscuGroupBody;
import com.silver.chat.network.requestbean.ExpelDiscuMemberBody;
import com.silver.chat.network.requestbean.ForgetPasswordBean;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.requestbean.LoginRequest;
import com.silver.chat.network.requestbean.RegisterRequest;
import com.silver.chat.network.requestbean.SetDiscuMsgRemindBody;
import com.silver.chat.network.requestbean.SetGroupManagerBody;
import com.silver.chat.network.responsebean.AddGroupMemBean;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.network.responsebean.LoginRequestBean;
import com.silver.chat.network.responsebean.QueryUserInfoBean;
import com.silver.chat.network.responsebean.SearchGroupBean;
import com.silver.chat.network.responsebean.SearchIdBean;
import com.silver.chat.network.responsebean.UpdateUserInfoBean;
import com.silver.chat.network.responsebean.UserInfoBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by joe on 2017/4/26.
 * 定义当前项目的所有业务逻辑的请求方法
 */

public interface ApiService {

    //验证手机是否注册
    @GET("imx/{version}/user/account/{mobile}")
    Call<BaseResponse> checkPhoneCode(@Path("version") String version, @Path("mobile") String phone);

    //获取验证码
    @GET("imx/{version}/sms/{phone}/{genre}")
    Call<BaseResponse> registCode(@Path("version") String version, @Path("phone") String phone, @Path("genre") int genre);

    //注册
    @POST("imx/{version}/user/register")
    Call<BaseResponse> registPhone(@Path("version") String version, @Body RegisterRequest registerRequest);

    //登录
    @POST("imx/{version}/user/login")
    Call<BaseResponse<LoginRequestBean>> goLogin(@Path("version") String version, @Body LoginRequest loginRequest);

    //退出登陆
    @GET("imx/{version}/user/logout")
    Call<BaseResponse> outLogin(@Path("version") String version,@Header("token") String token);

    //获取用户信息
    @GET("imx/{version}/user/information")
    Call<BaseResponse<UserInfoBean>> userInfo(@Path("version") String version, @Header("token") String token);

    //修改用户信息
    @POST("imx/{version}/user/update/information")
    Call<BaseResponse<UpdateUserInfoBean>> updateInfo(@Path("version") String version, @Header("token") String token, @Body UpdateUserInfoBean userinfobean);

    //忘记密码
    @POST("imx/{version}/user/back/password")
    Call<BaseResponse<ForgetPasswordBean>> backpassword(@Path("version") String version, @Body ForgetPasswordBean forgetPasswordBean);

    //分页获取好友列表
    @GET("imx/{version}/userfriend/{userId}/{page}/{count}")
    Call<BaseResponse<ArrayList<ContactListBean>>> contactList( @Path("version") String version, @Path("userId") String userId, @Path("page") String page, @Path("count") String count,@Header("token")String token);

    //获取全部好友列表
    @GET("imx/{version}/userfriend/{userId}")
    Call<BaseResponse<ArrayList<ContactListBean>>> allContact( @Path("version") String version, @Path("userId") String userId,@Header("token")String token);

    //创建群组
    @POST("imx/{version}/user/group")
    Call<BaseResponse<CreatGroupBean>> creatgroup(@Path("version") String version, @Header("token") String token, @Body CreatGroupBean creatGroupBean);

    //获取已经加入的群信息列表
    @POST("imx/{version}/user/group/addgrouplist")
    Call<BaseResponse<ArrayList<GroupBean>>> joinedGroupList(@Path("version")String version, @Body JoinedGroupRequest joinedGroupRequest, @Header("token")String token);

    //创建讨论组
    @POST("imx/{version}/user/discugroup/creatediscugroup")
    Call<BaseResponse<CreatGroupBean>> creatdiscugroup(@Path("version") String version, @Header("token") String token, @Body CreatGroupBean creatGroupBean);

//    //获取好友信息
//    @GET("imx/{version}/friendinfo/{userId}/{friendId}")
//    Call<BaseResponse<List<FriendInfo>>> friendinfo(@Header("token") String token, @Path("version") String version, @Path("userId") String userId, @Path("friendId") String friendId);

    //申请添加好友
    @PUT("imx/leaf/friend/{userId}/{friendId}/{comment}")
    Call<BaseResponse> addFriend(@Path("userId")String userId, @Path("friendId")String friendId, @Path("comment")String comment, @Header("token")String token);

    //搜索用户
    @GET("imx/leaf/searchuser/{type}/{condition}/{page}/{count}")
    Call<BaseResponse<ArrayList<SearchIdBean>>>searchUser(@Path("type")String type,@Path("condition")String condition,@Path("page")String page,@Path("count")String count,@Header("token")String token);

    //删除好友
    @DELETE("imx/leaf/removebuddy/{userId}/{friendId}/{appName}")
    Call<BaseResponse> deleteFriend(@Header("token")String token,@Path("userId")String userId,@Path("friendId")String friendId,@Path("appName")String appName);

    //搜索群组
    @GET("imx/leaf/user/group/search/{condition}/{page}/{count}")
    Call<BaseResponse<SearchGroupBean>> searchGroup(@Header("token")String token,@Path("condition")String condition,@Path("page")String page,@Path("count")String count);

    //申请添加群组
    @POST("imx/leaf/user/group/application")
    Call<BaseResponse> addGroup(@Header("token")String token, @Body AskJoinGroup askJoinGroup);

    //上传头像
    @POST("imicom/uploadImIcom")
    Call<BaseResponse>upLoadHead(@Header("token")String token, @Part MultipartBody.Part file);

    //获取群成员
    @GET("/imx/leaf/user/group/members/{groupId}")
    Call<BaseResponse<ArrayList<GroupMemberBean>>> getGroupMember(@Header("token")String token,@Path("groupId")String groupId);

    //修改好友备注
    @POST("imx/leaf/friendsnote/{userId}/{friendId}/{note}")
    Call<BaseResponse>revampFriendName(@Header("token")String token,@Path("userId")String userId,@Path("friendId")String friendId,@Path("note")String note);

    //屏蔽好友
    @POST("imx/leaf/shielding/{userId}/{friendId}")
    Call<BaseResponse>shieldFriend(@Header("token")String token,@Path("userId")String userId,@Path("friendId")String friendId);

    //拒绝好友申请
    @DELETE("imx/leaf/refusedto/{userId}/{friendId}")
    Call<BaseResponse>repulseFriendAdd(@Header("token")String token,@Path("userId")String userId,@Path("friendId")String friendId);

    //通过好友申请
    @POST("imx/leaf/throughfriend/through")
    Call<BaseResponse>agreeFriend(@Header("token")String token,@Body AgreeFriendAddBody agreeFriendAddBody);

    //通过用户Id查询用户信息
    @GET("imx/leaf/user/userinfo/{userId}")
    Call<BaseResponse<QueryUserInfoBean>>idQueryUserInfo(@Header("token")String token, @Path("userId")String userId);

    //邀请好友加入讨论组
    @POST("imx/leaf/user/discugroup/adddiscu")
    Call<BaseResponse>addFrDiscuGroup(@Header("token")String token, @Body AddFriendDiscuBody addFriendDiscuBean);

    //退出讨论组
    @GET("imx/leaf/discugroup/quite")
    Call<BaseResponse>exitDiscuGroup(@Header("token")String token, @Body ExitDiscuGroupBody exitDiscuGroupBody);

    //踢出讨论组成员
    @GET("imx/leaf/discugroup/kickmember")
    Call<BaseResponse>expelDiscuMember(@Header("token")String token, @Body ExpelDiscuMemberBody expelDiscuMember);

    //设置讨论组消息提醒
    @POST("imx/leaf/discugroup/reminderset")
    Call<BaseResponse>setDiscuMsgRemind(@Header("token")String token, @Body SetDiscuMsgRemindBody setDiscuMsgRemindBody);

    //设置群管理员(群主权限)
    @POST("imx/leaf/user/group/creategroupmanager")
    Call<BaseResponse>setGroupManager(@Header("token")String token, @Body SetGroupManagerBody setGroupManagerBody);

    //取消群管理员(群主权限)
    @POST("imx/leaf/user/group/deletegroupmanager")
    Call<BaseResponse>deleteGroupManager(@Header("token")String token, @Body SetGroupManagerBody setGroupManagerBody);

    //邀请好友入群(群主，管理员权限)
    @POST("imx/{version}/user/group/addmember")
    Call<BaseResponse> addGroupMember(@Path("version") String version,@Header("token")String token, @Body AddGroupMemBean addGroupMemBean);

    //解散(群主权限)
    @POST("imx/leaf/user/group/groupdelete")
    Call<BaseResponse> GroupDelete(@Body SetGroupManagerBody setGroupManagerBody);


}
