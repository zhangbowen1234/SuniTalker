package com.silver.chat.ui.contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;

public class NewFriendActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_friend;
    }

    @Override
    protected void initView() {
        super.initView();
        mBack = (ImageView) findViewById(R.id.title_left_back);


    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_left_back:
                finish();
                break;

        }
    }
}
