package com.silver.chat.ui.login;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMLoginManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.RegisterRequest;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ScreenManager;
import com.silver.chat.util.TimeCountUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.MyLineEditText;

import static com.silver.chat.util.Utils.context;


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

        uPhone = getIntent().getStringExtra("uPhone");
        tv_send_number.setText("短信已发送至" + uPhone);

        mBtnReg.setOnClickListener(this);
        mBtnAuthCode.setOnClickListener(this);
        mReturnLast.setOnClickListener(this);
        mBtnAuthCodeOther.setOnClickListener(this);

//        mAuthCode.addTextChangedListener(new EditChangedListener());
        //计时器
        TimePiece();
        mSetPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                 //当最大字符大于32时，进行字段的截取，并进行提示字段的大小
                int mTextMaxlenght = 0;
                Editable editable = mSetPwd.getText();
                String str = editable.toString().trim();
                //得到最初字段的长度大小，用于光标位置的判断
                int selEndIndex = Selection.getSelectionEnd(editable);
                // 取出每个字符进行判断，如果是字母数字和标点符号则为一个字符加1，
                //如果是汉字则为两个字符
                for (int i = 0; i < str.length(); i++) {
                    char charAt = str.charAt(i);
                    //32-122包含了空格，大小写字母，数字和一些常用的符号，
                    //如果在这个范围内则算一个字符，
                    //如果不在这个范围比如是汉字的话就是两个字符
                    if (charAt >= 32 && charAt <= 122) {
                        mTextMaxlenght++;
                    } else {
                        mTextMaxlenght += 2;
                    }
                    // 当最大字符大于16时，进行字段的截取，并进行提示字段的大小
                    if (mTextMaxlenght > 16) {
                        // 截取最大的字段
                        String newStr = str.substring(0, i);
                        mSetPwd.setText(newStr);
                        // 得到新字段的长度值
                        editable = mSetPwd.getText();
                        int newLen = editable.length();
                        if (selEndIndex > newLen) {
                            selEndIndex = editable.length();
                        }
                        // 设置新光标所在的位置
                        Selection.setSelection(editable, selEndIndex);
                        ToastUtils.showMessage(context,"至多输入16位");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mAgainSetPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                int mTextMaxlenght = 0;
                Editable editable = mSetPwd.getText();
                String str = editable.toString().trim();
                int selEndIndex = Selection.getSelectionEnd(editable);
                for (int i = 0; i < str.length(); i++) {
                    char charAt = str.charAt(i);
                    if (charAt >= 32 && charAt <= 122) {
                        mTextMaxlenght++;
                    } else {
                        mTextMaxlenght += 2;
                    }
                    if (mTextMaxlenght > 16) {
                        String newStr = str.substring(0, i);
                        mSetPwd.setText(newStr);
                        editable = mSetPwd.getText();
                        int newLen = editable.length();
                        if (selEndIndex > newLen) {
                            selEndIndex = editable.length();
                        }
                        Selection.setSelection(editable, selEndIndex);
                        ToastUtils.showMessage(context,"至多输入16位");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
                /**
                 * 注册
                 */
                goReginst();
                break;
            case R.id.btn_auth_code:
                //重新发送验证码并计时
                TimePiece();
                /**
                 * 重新获取验证码
                 */
                sendSmsCode(uPhone);
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

    private void goReginst() {
        SSIMLoginManger.goReginst(mContext,Common.version, RegisterRequest.getInstance(), new ResponseCallBack<BaseResponse>() {

            @Override
            public void onSuccess(BaseResponse baseResponse) {
                ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
                PreferenceUtil.getInstance(mContext).setString("phone", uPhone);
                PreferenceUtil.getInstance(mContext).setString("pwd", uSetP);
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

    private void sendSmsCode(String uPhone) {
        SSIMLoginManger.userReginstCode(mContext,Common.version, uPhone, Common.RegType, new ResponseCallBack<BaseResponse>() {


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
