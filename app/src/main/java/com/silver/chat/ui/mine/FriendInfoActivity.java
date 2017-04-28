package com.silver.chat.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.ui.contact.ContactChatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Fandy on 2016/12/1 09:51
 * 邮箱：fandy618@hotmail.com
 */

public class FriendInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvName,mChat;
    private ImageView mIvBack;
    private String contactName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_firend_info;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        tvName = (TextView) findViewById(R.id.tv_name);
        mChat = (TextView)findViewById(R.id.tv_detail);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        contactName = intent.getStringExtra("contactName");
        tvName.setText(contactName + "");
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvBack.setOnClickListener(this);
        mChat.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_detail:
                Intent mIntent = new Intent(mContext,ContactChatActivity.class);
                mIntent.putExtra("contactName",contactName);
                startActivity(mIntent);
                break;
        }
    }

}
