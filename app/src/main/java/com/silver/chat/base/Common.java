package com.silver.chat.base;

/**
 * Created by lenovo on 2017/4/28.
 */

public class Common {

    public final static String APPKEY = "651a25166f6e36e3" ;
    public final static String APPSECRET = "MNyhZZdmrsL_E7v4Fv1Qgx-sziU=" ;

    /**
     * 版本号
     */
    public final static String version = "leaf";
    /**
     * 短信请求类型
     */
    public final static int RegType = 1; //注册
    public final static int LoginType = 2; //登录
    public final static int RevampPwdType = 3; //修改
    public final static int RecoverPwdType = 3; //找回

    /**
     * 搜索方式类型
     */
    public final static String Login = "1"; //注册
    public final static String Phone = "2"; //登录
    public final static String Email = "3"; //修改

    /**
     * 重新登录提示
     */
//    public final static String AnewLogin = "请重新登录";
    public final static int AnewLoginCode = 300;

    public static final String IMAGE_PATH = "IMG";// 头像文件的缓存文件
    public static final int OPEN_CAMERA_CODE = 12;// 相机
    public static final int OPEN_GALLERY_CODE = 13;// 相册
    public static final int CROP_PHOTO_CODE = 14;// 裁剪
}
