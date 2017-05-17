package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.silver.chat.R;
import com.silver.chat.adapter.GroupMemAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.network.responsebean.GroupMemberBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/11.
 */
public class GroupMemberActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.image_seach)
    ImageView imageSeach;
    @BindView(R.id.rl_seach)
    RelativeLayout rlSeach;
    @BindView(R.id.listview)
    ListView listview;
    private int groupid;
    private int privilege;
    //群成员列表的集合
    private ArrayList<GroupMemberBean> lists;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupmem;
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        groupid = intent.getIntExtra("groupid", -1);
        privilege = intent.getIntExtra("privilege", -1);
        lists = (ArrayList<GroupMemberBean>) intent.getSerializableExtra("lists");
        //listview.setAdapter(new GroupMemAdapter(lists));
    }

    @Override
    protected void initData() {
        super.initData();
        getGroupMenber();
    }

    /**
     * 获取群成员列表
     */
    private void getGroupMenber() {

    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @OnClick({R.id.title_left_back, R.id.image_seach, R.id.rl_seach, R.id.activity_new_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.image_seach:
                break;
            case R.id.rl_seach:
                break;
            case R.id.activity_new_friend:
                break;
        }
    }
}
