package com.silver.chat.ui.mine.setting;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.ToastUtils;

import static com.silver.chat.util.Utils.context;

/**
 * 作者：Fandy on 2016/12/9 11:34
 * 邮箱：fandy618@hotmail.com
 * <p>
 * 账户
 */

public class AccountActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 返回
     */
    private ImageView mIvBack;
    /**
     * 完成
     */
    private TextView mTvFinish;
    /**
     * 头像
     */
    private RelativeLayout mRlyAvatar;
    private ImageView mIvAvatar;
    /**
     * 名字
     */
    private LinearLayout mLlyName;
    private EditText mTvName,mEdPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvFinish = (TextView) findViewById(R.id.iv_save);
        mRlyAvatar = (RelativeLayout) findViewById(R.id.rly_avatar);
        mIvAvatar = (ImageView) findViewById(R.id.iv_avatar);
        mLlyName = (LinearLayout) findViewById(R.id.lly_name);
        mTvName = (EditText) findViewById(R.id.tv_name);
        mEdPhone = (EditText) findViewById(R.id.tv_phone);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvBack.setOnClickListener(this);
        mTvFinish.setOnClickListener(this);
        mRlyAvatar.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mTvName.setSelection(mTvName.getText().length());
        mEdPhone.setSelection(mEdPhone.getText().length());
        mTvName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = mEdPhone.getText();
                int len = editable.length();
                //得到最初字段的长度大小，用于光标位置的判断
                int selEndIndex = Selection.getSelectionEnd(editable);
                // 取出每个字符进行判断，如果是字母数字和标点符号则为一个字符加1，
                // 当最大字符大于11时，进行字段的截取，并进行提示字段的大小
                if (len > 11) {
                    String str = editable.toString();
                    // 截取最大的字段
                    String newStr = str.substring(0, 11);
                    mEdPhone.setText(newStr);
                    // 得到新字段的长度值
                    editable = mEdPhone.getText();
                    int newLen = editable.length();
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    // 设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                    ToastUtils.showMessage(context, "至多输入11个字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEdPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_save:
                finish();
                break;
            case R.id.rly_avatar:
                break;
        }
    }
}
