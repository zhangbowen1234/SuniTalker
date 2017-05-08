package com.silver.chat.ui.login;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMUserManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.requestbean.RegisterRequest;
import com.silver.chat.util.PreferenceUtil;
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
    private Button mBtnReg, mBtnAuthCode, mBtnRegPhone, mBtnAuthCodeOther;
    private TextView mReturnLast, tv_send_number;
    private String uSetP, uASetP, uAuthC;
    TimeCountUtil timeCountUtil;//倒计时工具
    //    private final int charMaxNum = 6; // 允许输入的字数
    private String uPhone;

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
    protected void initData() {
        super.initData();
        uPhone = getIntent().getStringExtra("uPhone");
        tv_send_number.setText("短信已发送至" + uPhone);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register_id:

                uSetP = mSetPwd.getText().toString();
                uASetP = mAgainSetPwd.getText().toString();
                uAuthC = mAuthCode.getText().toString();

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
                RegisterRequest.getInstance().setPhone(uPhone);
                RegisterRequest.getInstance().setPws(uSetP);
                RegisterRequest.getInstance().setRepws(uASetP);
                RegisterRequest.getInstance().setSmsCode(uAuthC);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SSIMUserManger.goReginst(Common.version, RegisterRequest.getInstance(), new ResponseCallBack<BaseResponse>() {
                            @Override
                            public void onSuccess(BaseResponse baseResponse) {
                                ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
                                PreferenceUtil.getInstance(mContext).setString("phone",uPhone);
                                PreferenceUtil.getInstance(mContext).setString("pwd",uSetP);
                                finish();
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
                    }
                }).start();

                break;
            case R.id.btn_auth_code:
                //重新发送验证码并计时
                TimePiece();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendSmsCode(uPhone);
                    }
                }).start();
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

    private void sendSmsCode(String uPhone) {
        SSIMUserManger.userReginstCode(Common.version, uPhone, Common.RegType, new ResponseCallBack<BaseResponse>() {
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

}
