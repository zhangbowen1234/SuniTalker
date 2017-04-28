package com.silver.chat.ui.contact;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.entity.DataServer;
import com.silver.chat.entity.GroupEntity;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by joe on 2017/4/25.
 */

public class GroupChatActivity extends BaseActivity {

    ImageView titleLeftBack;
    ViewPager viewpager;
    RecyclerView recycleview;
    ImageView imageSeach;


    @Override
    protected void initView() {
        titleLeftBack = (ImageView) findViewById(R.id.title_left_back);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
        imageSeach = (ImageView) findViewById(R.id.image_seach);


        recycleview.setLayoutManager(new LinearLayoutManager(this));
        List<GroupEntity> chatDatagroup = DataServer.getChatDatagroup();
        //recycleview.setAdapter(new MyGroupAdapter(chatDatagroup));
    }

    @OnClick({R.id.title_left_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupchat;
    }


}
