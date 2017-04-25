package com.silver.chat.ui.chat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.silver.chat.R;
import com.silver.chat.adapter.SearchChatRecordApater;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.view.SearchLayout;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Fandy on 2016/11/22 18:05
 * 邮箱：fandy618@hotmail.com
 * 聊天记录搜索界面
 */

public class SearchChatRecordActivity extends BaseActivity implements SearchLayout.OnSearchClickListener {

    private SearchLayout mSearchLayout;
    private RecyclerView mRecycleContent;
    private SearchChatRecordApater mApater;
    private List<ChatBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_chat_record;
    }

    @Override
    protected void initView() {
        super.initView();
        mSearchLayout = (SearchLayout) findViewById(R.id.search_layout);
        mRecycleContent = (RecyclerView) findViewById(R.id.recyle_content);
        mRecycleContent.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initListener() {
        super.initListener();
        mSearchLayout.setOnSearchClickListener(this);
        mRecycleContent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mList.get(position).getItemType() == 1){
                    ToastUtil.toastMessage(mContext, "系统position=" + position);
                }else if(mList.get(position).getItemType() == 2){
                    ToastUtil.toastMessage(mContext, "单聊position=" + position);

                }else if ((mList.get(position).getItemType() == 3)){
                    ToastUtil.toastMessage(mContext, "群聊position=" + position);
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
//        mList.addAll(DataServer.getChatData());
        mApater = new SearchChatRecordApater(mList);
        mRecycleContent.setAdapter(mApater);
    }

    @Override
    public void onBack() {
        finish();
    }
}
