package com.silver.chat.ui.chat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.silver.chat.R;
import com.silver.chat.adapter.ChatApater;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.entity.DataServer;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.OnItemClickListener;
import com.silver.chatsdk.SSIMClient;
import com.silver.chatsdk.service.bean.SigninRequest;
import com.silver.chatsdk.service.bean.SigninResponse;
import com.silver.chatsdk.service.manager.SSIMEngine;
import com.silver.chatsdk.service.bean.RegisterRequest;
import com.silver.chatsdk.service.bean.RegisterResponse;
import com.silver.chatsdk.service.bean.ResponseCallBackInterface;
import com.silver.chatsdk.service.network.SSIMHttpEngine;
import com.silver.chatsdk.service.network.SSIMHttpsEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Fandy on 2016/11/14 14:14
 * 邮箱：fandy618@hotmail.com
 */

public class ChatRecordFragment extends BasePagerFragment {

    private RecyclerView mRecycleContent;
    private ChatApater mChatApater;
    private List<ChatBean> mList;


    public static ChatRecordFragment newInstance() {
        Bundle args = new Bundle();
        ChatRecordFragment fragment = new ChatRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecycleContent = (RecyclerView) view.findViewById(R.id.recyle_content);
        mRecycleContent.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        mList.addAll(DataServer.getChatData());
        mChatApater = new ChatApater(mList);
        mRecycleContent.setAdapter(mChatApater);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRecycleContent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
//                SSIMClient.getInstance().creatAccount(new ResponseCallBackInterface() {
//                    @Override
//                    public void onSuccess(Object o) {
//                        Log.i("success", "success");
//                    }
//
//                    @Override
//                    public void onFailed(int code) {
//                        Log.i("onFailed", "onFailed");
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });

                //调用无参的getInstance()方法时，初始化参数为默认设置参数
                SSIMEngine engine = SSIMEngine.getInstance();

//                SSIMHttpConfig httpConfig = new SSIMHttpConfig("221.122.16.113","7303",true,false);
//                SSIMSocketConfig socketConfig = new SSIMSocketConfig("221.122.16.113","7301");
//                SSIMEngine engine = SSIMEngine.getInstance(new SSIMConfig(httpConfig,socketConfig));

                engine.ssimGetUserManager(getContext()).ssimRegister(new ResponseCallBackInterface<RegisterResponse>() {
                    @Override
                    public void onSuccess(RegisterResponse registerResponse) {

                    }

                    @Override
                    public void onFailed(int code) {

                    }

                    @Override
                    public void onError() {

                    }
                }, new RegisterRequest("13146733521", "123456", "123456"));

                engine.ssimGetUserManager(getContext()).ssimSignin(new ResponseCallBackInterface<SigninResponse>() {
                    @Override
                    public void onSuccess(SigninResponse signinResponse) {

                    }

                    @Override
                    public void onFailed(int code) {

                    }

                    @Override
                    public void onError() {

                    }
                }, new SigninRequest("13146733521", "12345"));
            }
        });
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_chat_record;
    }

}
