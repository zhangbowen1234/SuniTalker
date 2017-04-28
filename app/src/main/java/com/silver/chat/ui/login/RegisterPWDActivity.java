package com.silver.chat.ui.login;

import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.ScreenManager;
import com.silver.chat.util.TimeCountUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.MyLineEditText;


/**
 * Created by hibon on 2016/11/14.
 * 注册账号
 */
public class RegisterPWDActivity extends BaseActivity implements View.OnClickListener {

    private EditText mSetPwd, mAgainSetPwd;
    private MyLineEditText mAuthCode;
    private Button mBtnReg, mBtnAuthCode, mBtnRegPhone,mBtnAuthCodeOther;
    private TextView mReturnLast,tv_send_number;
    private String uSetP, uASetP, uAuthC;
    TimeCountUtil timeCountUtil;//倒计时工具
//    private final int charMaxNum = 6; // 允许输入的字数


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_pwd;
    }




    private void TimePiece() {
        if (timeCountUtil == null) {
            timeCountUtil = new TimeCountUtil(this, 60000, 1000, mBtnAuthCode);
        }
        timeCountUtil.start();
    }


    protected void initView() {
        mSetPwd = (EditText) findViewById(R.id.set_pwd);
        mAgainSetPwd = (EditText) findViewById(R.id.again_set_pwd);
        mAuthCode = (MyLineEditText) findViewById(R.id.edit_auth_code);
        mBtnReg = (Button) findViewById(R.id.btn_register_id);
        mBtnAuthCode = (Button) findViewById(R.id.btn_auth_code);
        mReturnLast = (TextView) findViewById(R.id.return_last);
        mBtnAuthCodeOther = (Button) findViewById(R.id.btn_auth_code_other);
        tv_send_number = (TextView) findViewById(R.id.tv_send_number);


        mBtnReg.setOnClickListener(this);
        mBtnAuthCode.setOnClickListener(this);
        mReturnLast.setOnClickListener(this);
        mBtnAuthCodeOther.setOnClickListener(this);

//        mAuthCode.addTextChangedListener(new EditChangedListener());
        //计时器
        TimePiece();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register_id:

                uSetP = mSetPwd.getText().toString();
                uASetP = mAgainSetPwd.getText().toString();
                uAuthC = mAuthCode.getText().toString();
//                String autoCode = "123456";

                if (uSetP == null || "".equals(uSetP)) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "密码不能为空!");
                    return;
                }
                if (uSetP.length() < 6 || uSetP.length() > 16) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (uASetP == null || "".equals(uASetP)) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (uASetP.length() < 6 || uASetP.length() > 16) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (!(uSetP.equals(uASetP))) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "两次密码不一致!");
                    return;
                }
                if (uAuthC == null || "".equals(uAuthC)) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入验证码!");
                    return;
                }
//                if (!autoCode.equals(uAuthC)) {
//                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入正确的验证码!");
//                    return;
//                }
                break;
            case R.id.btn_auth_code:
                //重新发送验证码并计时
                TimePiece();

                break;
            case R.id.btn_auth_code_other:
                ScreenManager.getScreenManager().goBlackPage();
                finish();
                break;
            case R.id.return_last:
                ScreenManager.getScreenManager().goBlackPage();
                finish();
                break;
        }

    }


}
