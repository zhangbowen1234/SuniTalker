package com.silver.chat.ui.contact;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.adapter.NewFriendAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.entity.ContactMemberBean;

import java.util.ArrayList;

/**
 * 作者：hibon on 2017/4/16 14:14
 * 新朋友
 */

public class NewFriendActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private RecyclerView mNFRecyclerList;
    LinearLayoutManager linearLayoutManager;
    ArrayList<ContactMemberBean> newFriendList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_friend;
    }

    @Override
    protected void initView() {
        super.initView();
        mBack = (ImageView) findViewById(R.id.title_left_back);
        mNFRecyclerList = (RecyclerView) findViewById(R.id.new_friend_list);

        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newFriendList = new ArrayList<ContactMemberBean>();

        //设置布局管理器
        mNFRecyclerList.setLayoutManager(linearLayoutManager);
        NewFriendAdapter addFriend = new NewFriendAdapter(R.layout.item_new_friend,newFriendList);

    }

    @Override
    protected void initData() {


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
