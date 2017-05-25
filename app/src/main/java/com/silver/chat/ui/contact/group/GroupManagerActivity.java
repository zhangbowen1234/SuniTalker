package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/11.
 * 群管理Activity
 */
public class GroupManagerActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.bt_determine)
    Button btDetermine;
    @BindView(R.id.activity_new_friend)
    RelativeLayout activityNewFriend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupmanger;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }


    @OnClick({R.id.title_left_back, R.id.bt_determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.bt_determine:
                Intent intent = new Intent(mContext, GroupMangerActivity2.class);
                startActivity(intent);
                break;
        }
    }
}
