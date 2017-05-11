package com.silver.chat.ui.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMLoginManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.util.NumberUtils;
import com.silver.chat.util.ScreenManager;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.MyLineEditText;


/**
 * Created by hibon
 * 登录手机号
 */
public class RegisterPhoneActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnAuthCode;
    private ImageView qq, weixin, xinlang;
    private TextView mReturnLast;
    private MyLineEditText mUserPhone;
    private String uPhone;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_phone;
    }

    protected void initView() {

        mBtnAuthCode = (Button) findViewById(R.id.btn_auth_code);
        mUserPhone = (MyLineEditText) findViewById(R.id.user_phone);
        qq = (ImageView) findViewById(R.id.qq);
        weixin = (ImageView) findViewById(R.id.weixin);
        xinlang = (ImageView) findViewById(R.id.xinlang);
        mReturnLast = (TextView) findViewById(R.id.return_last);

        mBtnAuthCode.setOnClickListener(this);
        qq.setOnClickListener(this);
        weixin.setOnClickListener(this);
        xinlang.setOnClickListener(this);
        mReturnLast.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_auth_code:
                uPhone = mUserPhone.getText().toString().trim();
                if (uPhone == null || "".equals(uPhone)) {
                    ToastUtils.showMessage(RegisterPhoneActivity.this, "手机号不能为空!");
                    return;
                }
                if (uPhone.length() != 11 || !(NumberUtils.isMobileNum(uPhone))) {
                    ToastUtils.showMessage(RegisterPhoneActivity.this, "手机号不正确!");
                    return;
                }
//                //去后台请求获取验证码
//                getIndentifyCode();
                SSIMLoginManger.checkPhone(Common.version, uPhone, new ResponseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int statusCode = baseResponse.getStatusCode();
                        Log.d(TAG, statusCode + "");
                        if (statusCode == 1) { //未注册
                            /**
                             * 获取短信验证码
                             */
                            sendSmsCode(uPhone);
                        }
                        if (statusCode == 2) {//已注册
                            ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
                        }
                    }

                    @Override
                    public void onFailed(BaseResponse baseResponse) {
                        ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
                    }

                    @Override
                    public void onError() {
                        ToastUtils.showMessage(mContext, "网络连接错误");
                    }
                });
                Intent regPIntent = new Intent(RegisterPhoneActivity.this, RegisterPWDActivity.class);
                regPIntent.putExtra("uPhone", uPhone);
                ScreenManager.getScreenManager().StartPage(RegisterPhoneActivity.this, regPIntent, true);
                break;

            case R.id.qq:
                ToastUtils.showMessage(this, "尚未开通此功能");
                break;
            case R.id.xinlang:
                ToastUtils.showMessage(this, "尚未开通此功能");
                break;

            case R.id.weixin:
                ToastUtils.showMessage(this, "尚未开通此功能");
                break;

            case R.id.return_last:
                // 点击登录重新回到登录页面
                ScreenManager.getScreenManager().goBlackPage();
                finish();
                break;
        }
    }

    private void sendSmsCode(String uPhone) {
        SSIMLoginManger.userReginstCode(Common.version, uPhone, Common.RegType, new ResponseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                Log.e(TAG, baseResponse.getStatusMsg());
                ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
            }

            @Override
            public void onFailed(BaseResponse baseResponse) {
                ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
            }

            @Override
            public void onError() {
                Log.e(TAG, "onError");
                ToastUtils.showMessage(mContext, "网络连接错误");
            }
        });

    }


//    private void getIndentifyCode() {
//        SSIMLoginManger.userReginstCode(new ResponseCallBack<BaseResponse>() {
//            @Override
//            public void onSuccess(BaseResponse baseResponse) {
//                Toast.makeText(mContext, "获取验证码成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailed(BaseResponse baseResponse) {
//                //Log.e(TAG, code+"" );
//                Toast.makeText(mContext, "获取验证码失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError() {
//                //Log.e(TAG, "onError" );
//            }
//        }, mUserPhone.getText().toString());
//    }
}
