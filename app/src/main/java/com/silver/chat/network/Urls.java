package com.silver.chat.network;

/**
 * Created by 张博文 on 2017/4/21.
 */

public class Urls {
//    public static final String SERVER = "http://192.168.10.132:7102/";
    public static final String SERVER = "http://192.168.10.215:7102/";
//    public static final String SERVER = "http://imx.users.docker.sspaas.net/";
    //用户
    public static final String URL_SMS = SERVER + "imx/version/sms";// 发送验证码
    public static final String URL_ISPHONE_REGISTER = SERVER + "imx/version/user/account";//验证手机号是否已注册
    public static final String URL_REGISTER = SERVER + "imx/version/user/register";//用户注册
    public static final String URL_LOGIN = SERVER + "imx/version/user/login";//用户登陆
    public static final String URL_USER_INFO = SERVER + "imx/version/user/information";//获取用户信息
    public static final String URL_CHANGE_INFO = SERVER + "imx/version/user/update/information";//修改用户信息
    public static final String URL_UPLOAD_HEAD = SERVER + "user/upUserIcon";//上传用户头像
    public static final String URL_FINISH_LOGIN = SERVER + "imx/version/user/logout";//用户退出登录
    public static final String URL_FORGET_PWD = SERVER + "imx/version/user/back/password";//忘记密码
    public static final String URL_FIND_PWD = SERVER + "imx/version/user/update/password";//修改密码
    //好友
    public static final String URL_GET_BUDDY_LIST = SERVER + "imx/leaf/userfriend";//分页获取好友列表
    public static final String URL_GET_FRINED_INFO = SERVER + "imx/leaf/friendinfo";//获取好友信息
    public static final String URL_SEARCHUSER = SERVER + "imx/leaf/searchuser";//搜索用户
    public static final String URL_FRIENDS_NOTE = SERVER + "imx/leaf/friendsnote";//修改好友备注
    public static final String URL_SHIELDING = SERVER + "imx/leaf/shielding";//屏蔽
    public static final String URL_FRIEND = SERVER + "imx/leaf/friend";//申请添加好友
    public static final String URL_THROUGH = SERVER + "imx/leaf/through";//通过好友申请
    public static final String URL_REMOVE_BUDDY = SERVER + "imx/leaf/removebuddy";//删除好友
    public static final String URL_REFUSEDTO = SERVER + "imx/leaf/refusedto";//拒绝好友申请
    //群组
    public static final String URL_GROUP_APPLY = SERVER + "imx/leaf/user/group/apply";//审核入群申请（群主权限)
    public static final String URL_GROUP_ASSIGNER = SERVER + "imx/leaf/user/group/assigner";//群主转让（群主权限）
    public static final String URL_GROUP_KICKMEMBER = SERVER + "imx/leaf/user/group/kickmember";//踢出群成员（群主权限）
    public static final String URL_GROUP_GROUPDELETE = SERVER + "imx/leaf/user/group/groupdelete";//解散群（群主权限）
    public static final String URL_GROUP_ANNOUNCEMENT = SERVER + "imx/leaf/user/group/announcement";//设置群信息（群主权限，公告）
    public static final String URL_GROUP_INTRODUCE = SERVER + "imx/leaf/user/group/introduce";//设置群信息（群主权限，群介绍）
    public static final String URL_GROUP_CREATGROUPMANAGER = SERVER + "imx/leaf/user/group/creategroupmanager";//设置群管理员（群主权限）
    public static final String URL_GROUP_DELETEGROUPMANAGER = SERVER + "imx/leaf/user/group/deletegroupmanager";//取消群管理员（群主权限）
    public static final String URL_GROUP_ADDGROUPMETHOD = SERVER + "imx/leaf/user/group/addgroupmethod";//修改加群方式（群主权限）

    public static final String URL_GROUP_ADDGROUPLIST = SERVER + "imx/leaf/user/group/addgrouplist";//已加入的群列表
    public static final String URL_GROUP_SEARCH = SERVER + "imx/version/user/group/search";//搜索群
    public static final String URL_GROUP = SERVER + "imx/version/user/group";//创建群组
    public static final String URL_GROUP_NOTIFICATION = SERVER + "imx/version/user/group/notification";//修改群消息设置
    public static final String URL_GROUP_APPLICATION = SERVER + "imx/leaf/user/group/application";//申请入群
    public static final String URL_GROUP_MEMBERS = SERVER + "imx/version/user/group/members";//获取群成员
    public static final String URL_GROUP_NICKNAME = SERVER + "imx/version/user/group/nickname";//设置群昵称
    public static final String URL_GROUP_REMARK = SERVER + "imx/version/user/group/remark";//设置群备注
    public static final String URL_GROUP_INVITE_FRIEND = SERVER + "imx/version/user/group/remark";//邀请好友入群
//    public static final String URL_GROUP_FINISH = SERVER + "imx/:version/user/group/quit/:groupId";//退出群
//    public static final String URL_GROUP_INFORMATION = SERVER + "imx/:version/user/group/information";//获取群信息
//    public static final String URL_GROUP_ICON = "http://cloud.docker.sspaas.net/sspaas-cloud/imicom/uploadImIcom";//设置/修改群头像

    //聊天室


}
