package com.silver.chat.ui.chat;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.library.listener.OnRecyclerItemClickListener;
import com.silver.chat.R;
import com.silver.chat.adapter.ChatApater;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.ui.chat.notification.GroupNotificationActivity;
import com.silver.chat.ui.contact.ContactChatActivity;
import com.silver.chat.ui.contact.group.GroupChatActivity;
import com.silver.chat.view.SearchLayout;
import com.silver.chat.view.recycleview.pulltorefreshable.WSRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hibon on 2016/11/22 18:05
 * 聊天记录搜索界面
 */

public class SearchChatRecordActivity extends BaseActivity implements SearchLayout.OnSearchClickListener {

    private SearchLayout mSearchLayout;
    private WSRecyclerView mRecycleContent;
    private ChatApater mApater;
    private List<ChatBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_chat_record;
    }

    @Override
    protected void initView() {
        super.initView();
        mSearchLayout = (SearchLayout) findViewById(R.id.search_layout);
        mRecycleContent = (WSRecyclerView) findViewById(R.id.recyle_content);
        mRecycleContent.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initListener() {
        super.initListener();
        mSearchLayout.setOnSearchClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        mList.addAll(ChatApater.getChatData(mContext));
        mApater = new ChatApater(mList);
        mRecycleContent.setAdapter(mApater);
        mApater.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mApater.getItemViewType(position) == ChatBean.CHAT_SINGLR) {
                    Intent mIntent = new Intent(mContext, ContactChatActivity.class);
                    mIntent.putExtra("contactName", mList.get(position).getUserName());
                    mIntent.putExtra("friendId", mList.get(position).getUserId());
                    mIntent.putExtra("chatType", Common.PRIVAT);
                    startActivity(mIntent);
                } else if (mApater.getItemViewType(position) == ChatBean.CHAT_GROUP) {
                    Intent mIntent = new Intent(mContext, GroupChatActivity.class);
                    mIntent.putExtra("groupName", mList.get(position).getGroupName());
                    mIntent.putExtra("groupId", mList.get(position).getGroupId());
                    startActivity(mIntent);
                } else if (mApater.getItemViewType(position) == ChatBean.CHAT_GROUP_NOTICE) {
                    startActivity(GroupNotificationActivity.class);
                }
            }
        });
    }

    @Override
    public void onBack() {
        finish();
    }
}
