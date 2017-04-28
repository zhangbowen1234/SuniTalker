package com.silver.chat.ui.login;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.NumberUtils;
import com.silver.chat.util.ScreenManager;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CustomVideoView;

public class
LoginActivity extends BaseActivity implements View.OnClickListener {


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

    }

    @Override
    protected int getLayoutId() {
        Log.i("aaaaaaa", "saaaa");

        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
/**

 Retrofit retrofit = new Retrofit.Builder()
 .baseUrl("http://imx.users.docker.sspaas.net/")
 //增加返回值为String的支持
 //                        .addConverterFactory(ScalarConverterFactory.create())
 //增加返回值为Gson的支持(以实体类返回)
 .addConverterFactory(GsonConverterFactory.create())
 //增加返回值为Oservable<T>的支持
 //                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
 .build();

 RequestService requestServes = retrofit.create(RequestService.class);
 Call<String> call = requestServes.getContact("leaf", "13621153700", 1);
 call.enqueue(new Callback<String>() {
@Override public void onResponse(Call<String> call, Response<String> response) {
Log.e("===","return:"+response);
Log.e("===","return+call:"+call.toString());
}

@Override public void onFailure(Call<String> call, Throwable t) {
Log.e("===","失败"+call.toString());
}
});
 */



                uPhone = mUserPhone.getText().toString();
                uPwd = mUserPwd.getText().toString();
//                startActivity(MainActivity.class);

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

    @Override
    protected void onStart() {
        super.onStart();
        if (!mVideoView.isPlaying()) {
            mVideoView.start();
        }
    }
}
