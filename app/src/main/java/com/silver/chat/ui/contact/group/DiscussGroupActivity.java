package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.database.callback.EasyRun;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.DisscusBean;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.ui.contact.SearchContactActivity;
import com.silver.chat.ui.contact.group.GroupChatActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

import static android.R.attr.data;

/**
 * Created by Joe on 2017/5/8.
 */
public class DiscussGroupActivity extends BaseActivity{
    ImageView titleLeftBack;
    ImageView imageSeach;
    RecyclerView recyler;
    RelativeLayout rlSeach;
    ImageView ivDiscussGroup;
    private TextView tvTaoLunZu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discussgroup;
    }

    @Override
    protected void initView() {
        titleLeftBack = (ImageView) findViewById(R.id.title_left_back);
        imageSeach = (ImageView) findViewById(R.id.image_seach);
        recyler = (RecyclerView) findViewById(R.id.recyler);
        rlSeach = (RelativeLayout) findViewById(R.id.rl_seach);
        ivDiscussGroup = (ImageView)findViewById(R.id.iv_discussgroup);
        tvTaoLunZu = (TextView) findViewById(R.id.tv_taolunzu);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }
    @OnClick({R.id.title_left_back,R.id.rl_seach, R.id.iv_discussgroup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.rl_seach:
                startActivity(SearchContactActivity.class);
                break;
            case R.id.iv_discussgroup:
                startActivity(GroupChatActivity.class);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
