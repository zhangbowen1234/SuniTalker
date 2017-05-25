package com.silver.chat.ui.contact.group;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/11.
 * 添加管理员2
 */
public class GroupMangerActivity2 extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.bt_determine)
    Button btDetermine;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupmanger2;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }



    @OnClick({R.id.title_left_back, R.id.bt_determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.bt_determine:
                break;
        }
    }
}
