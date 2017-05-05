package com.silver.chat.ui.login;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silver.chat.MainActivity;
import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMUserMange;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.LoginRequest;
import com.silver.chat.network.responsebean.LoginRequestBean;
import com.silver.chat.network.responsebean.UserInfoBean;
import com.silver.chat.util.NetUtils;
import com.silver.chat.util.NumberUtils;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ScreenManager;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CustomVideoView;

import java.util.UUID;

public class
LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final long DELAY_TIME = 600L;
    private TextView mGoReg, mForgot;
    private Button mBtnLogin;
    private EditText mUserPhone, mUserPwd;

    private String uPhone, uPwd;
    private CustomVideoView mVideoView;


    @Override
    protected void initView() {
        mGoReg = (TextView) findViewById(R.id.go_reg);
        mForgot = (TextView) findViewById(R.id.forgot_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mUserPhone = (EditText) findViewById(R.id.user_phone);
        mUserPwd = (EditText) findViewById(R.id.user_pwd);
        mVideoView = (CustomVideoView) findViewById(R.id.log_videoview);
        //监听
        mGoReg.setOnClickListener(this);
        mForgot.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        /**
         * 自动显示已登录过的账号
         */
        String userPhone = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERPHONE, "");
        String userPwd = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERPWD, "");
        if (userPhone != null || userPwd != null) {
            mUserPhone.setText(userPhone);
            mUserPwd.setText(userPwd);
        }
    }

    @Override
    protected void initData() {
        //super.initData();
        //获取播放资源
        mVideoView.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.logvideobig));

        //监听播放完成重新播放
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.start();
            }
        });
        //播放操作
        mVideoView.start();

        //获取设备 UUID 号码
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        PreferenceUtil.getInstance(this).setString("androidID", uniqueId);
        /**
         * 是否自动登陆
         */
        redirectByTime();
        if (NetUtils.isConnected(mContext)) {//是否联网
            getUserInfo();
        }else {
            ToastUtils.showMessage(mContext,"请检查网络");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 是否自动登录
     */
    private void redirectByTime() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!PreferenceUtil.getInstance(mContext).isFirst()) {
                    /**
                     * 是第一次登陆不做跳转
                     */
                } else {
                    if (PreferenceUtil.getInstance(mContext).isLog()) {
                        mBtnLogin.setClickable(false);
                        startActivity(MainActivity.class);
                        finish();
                    }

                }

            }
        }, DELAY_TIME);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                uPhone = mUserPhone.getText().toString().trim();
                uPwd = mUserPwd.getText().toString().trim();
                PreferenceUtil.getInstance(this).setFirst(true);
                if (uPhone == null || "".equals(uPhone)) {
                    ToastUtils.showMessage(LoginActivity.this, "请输入账号!");
                    return;
                }
                if (uPhone.length() != 11 || !(NumberUtils.isMobileNum(uPhone))) {
                    ToastUtils.showMessage(LoginActivity.this, "请输入正确手机号!");
                    return;
                }
                if (uPwd == null || "".equals(uPwd)) {
                    ToastUtils.showMessage(LoginActivity.this, "请输入密码!");
                    return;
                }
//                if (uPhone != null && uPwd != null) {
//                    preferenceUtil.setString("mobilenum", uPhone);
//                    preferenceUtil.setString("pwd", uPwd);
//                }
                LoginRequest.getInstance().setPhone(uPhone);
                LoginRequest.getInstance().setPassword(uPwd);
                LoginRequest.getInstance().setPhoneUuid(PreferenceUtil.getInstance(mContext).getString("androidID", ""));
                if (NetUtils.isConnected(this)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SSIMUserMange.goLogin(Common.version, LoginRequest.getInstance(), new ResponseCallBack<BaseResponse<LoginRequestBean>>() {
                                @Override
                                public void onSuccess(BaseResponse<LoginRequestBean> loginRequestBaseResponse) {
                                    ToastUtils.showMessage(mContext, loginRequestBaseResponse.getStatusMsg());
                                    /**
                                     * 登录成功后保存信息
                                     */
                                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.USERID, loginRequestBaseResponse.data.getUserId() + "");
                                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.TOKEN, loginRequestBaseResponse.data.getToken() + "");
                                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.IMTOKEN, loginRequestBaseResponse.data.getImToken() + "");
                                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.IMUSERID, loginRequestBaseResponse.data.getImUserId() + "");
                                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.AVATAR, loginRequestBaseResponse.data.getAvatar() + "");
                                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.NICKNAME, loginRequestBaseResponse.data.getNickName() + "");
                                    PreferenceUtil.getInstance(LoginActivity.this).setLog(true);
                                    PreferenceUtil.getInstance(mContext).setString("phone",uPhone);
                                    PreferenceUtil.getInstance(mContext).setString("pwd",uPwd);
                                    Message logMsg = new Message();
                                    logMsg.what = 0;
                                    logHandler.sendMessage(logMsg);

                                }

                                @Override
                                public void onFailed(BaseResponse<LoginRequestBean> loginRequestBaseResponse) {
                                    ToastUtils.showMessage(mContext, loginRequestBaseResponse.getStatusMsg());
                                }

                                @Override
                                public void onError() {
                                    ToastUtils.showMessage(mContext, "连接异常");
                                }
                            });
                        }
                    }).start();
                } else {
                    ToastUtils.showMessage(this, "请检查网络!");
                }


                break;
            case R.id.go_reg:
                Intent goRegIntent = new Intent(LoginActivity.this, RegisterPhoneActivity.class);
                ScreenManager.getScreenManager().StartPage(LoginActivity.this, goRegIntent, true);
                break;
            case R.id.forgot_pwd:
                Intent forgotIntent = new Intent(this, UserForgotPWDActivity.class);
                ScreenManager.getScreenManager().StartPage(LoginActivity.this, forgotIntent, true);
                break;
        }

    }

    Handler logHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: //跳转启动主页
                    getUserInfo();
                    PreferenceUtil.getInstance(LoginActivity.this).setLog(true);
                    startActivity(MainActivity.class);
                    break;
            }
        }

    };

    @Override
    protected void onStart() {
        super.onStart();
        if (!mVideoView.isPlaying()) {
            mVideoView.start();
        }
    }

    /**
     * 获取用户信息
     */
    private String token;
    private void getUserInfo(){
        token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        if (token != null && !"".equals(token)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SSIMUserMange.getUserInfo(Common.version, token, new ResponseCallBack<BaseResponse<UserInfoBean>>() {
                        @Override
                        public void onSuccess(BaseResponse<UserInfoBean> userInfoBeanBaseResponse) {
//                            ToastUtils.showMessage(mContext, userInfoBeanBaseResponse.getStatusMsg());
                            Log.d("userInfo",userInfoBeanBaseResponse.getStatusMsg());
                            /**
                             * 保存用户信息
                             */
                            PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.USERPHONE, userInfoBeanBaseResponse.data.getMobile() + "");
                            PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.AVATAR, userInfoBeanBaseResponse.data.getAvatar() + "");
                            PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.NICKNAME, userInfoBeanBaseResponse.data.getNickName() + "");
                            PreferenceUtil.getInstance(mContext).setInt(PreferenceUtil.SEX, userInfoBeanBaseResponse.data.getSex());
                            PreferenceUtil.getInstance(mContext).setInt(PreferenceUtil.SEX, userInfoBeanBaseResponse.data.getAge());
                            PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.SIGNATURE, userInfoBeanBaseResponse.data.getSignature());
                            PreferenceUtil.getInstance(mContext).setInt(PreferenceUtil.LEVEL, userInfoBeanBaseResponse.data.getLevel());
                        }

                        @Override
                        public void onFailed(BaseResponse<UserInfoBean> userInfoBeanBaseResponse) {
                            ToastUtils.showMessage(mContext, userInfoBeanBaseResponse.getStatusMsg());
//                            Log.d("userInfo",userInfoBeanBaseResponse.getStatusMsg());
                        }

                        @Override
                        public void onError() {
                            ToastUtils.showMessage(mContext, "连接失败");
                        }
                    });
                }
            }).start();
        }
    }

}
