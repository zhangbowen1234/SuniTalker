package com.silver.chat.ui.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.NumberUtils;
import com.silver.chat.util.ScreenManager;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.MyLineEditText;

/**
 * Created by zhenghp on 2016/11/14.
 */
public class UserForgotPWDActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnAuthCode;
    private EditText mSetPwd, mAgainSetPwd;
    private String uSetP, uASetP, uPhone;
    private MyLineEditText mUserPhone;
    private TextView mReturnLast;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgotpwd;
    }

    protected void initView() {

        mBtnAuthCode = (Button) findViewById(R.id.btn_auth_code);
        mSetPwd = (EditText) findViewById(R.id.set_pwd);
        mAgainSetPwd = (EditText) findViewById(R.id.again_set_pwd);
        mUserPhone = (MyLineEditText) findViewById(R.id.user_phone);
        mReturnLast = (TextView) findViewById(R.id.return_last);


        mBtnAuthCode.setOnClickListener(this);
        mReturnLast.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_auth_code:


                uSetP = mSetPwd.getText().toString();
                uASetP = mAgainSetPwd.getText().toString();
                uPhone = mUserPhone.getText().toString();
                String autoCode = "123456";

                if (uSetP == null || "".equals(uSetP)) {
                    ToastUtils.showMessage(UserForgotPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (uSetP.length() < 6 || uSetP.length() > 16) {
                    ToastUtils.showMessage(UserForgotPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (uASetP == null || "".equals(uASetP)) {
                    ToastUtils.showMessage(UserForgotPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (uASetP.length() < 6 || uASetP.length() > 16) {
                    ToastUtils.showMessage(UserForgotPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (!(uSetP.equals(uASetP))) {
                    ToastUtils.showMessage(UserForgotPWDActivity.this, "两次密码不一致!");
                    return;
                }

                if (uPhone == null || "".equals(uPhone)) {
                    ToastUtils.showMessage(UserForgotPWDActivity.this, "手机号不能为空!");
                    return;
                }
                if (uPhone.length() != 11 || !(NumberUtils.isMobileNum(uPhone))) {
                    ToastUtils.showMessage(UserForgotPWDActivity.this, "手机号不正确!");
                    return;
                }


                Intent forgotIntent = new Intent(this, ForgotVerificationActivity.class);
                ScreenManager.getScreenManager().StartPage(UserForgotPWDActivity.this, forgotIntent, true);

                break;
            case R.id.return_last:
                ScreenManager.getScreenManager().goBlackPage();
                finish();
                break;

        }
    }


}
