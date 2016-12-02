package com.silver.chat.ui.contact;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.silver.chat.R;
import com.silver.chat.adapter.ChatMessageAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.entity.ChatEntity;
import com.silver.chat.view.CircleImageView;
import com.silver.chat.view.TitleBarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ContactChatActivity extends BaseActivity implements View.OnClickListener {

    private CircleImageView mContactChatImg;
    private ImageButton mSendMsg, mEmoteBtn;
    private EditText inputEdit;
    private String contactName;
    private TitleBarView mTitleBar;
    private RecyclerView mChatMsgList;
    private RelativeLayout mShowHead;
    private List<ChatEntity> chatList;
    private ChatMessageAdapter chatMessageAdapter;
    private ViewPager mFaceViewPager;
    private LinearLayout mExpression;
    private Handler handler;
    private int friendId = 894446774;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_chat;
    }


    @Override
    protected void initView() {
        super.initView();

        mContactChatImg = (CircleImageView) findViewById(R.id.contact_chat_img);
        mSendMsg = (ImageButton) findViewById(R.id.chat_send_msg);
        mTitleBar = (TitleBarView) findViewById(R.id.title_bar);
        mChatMsgList = (RecyclerView) findViewById(R.id.recyle_content);
        mEmoteBtn = (ImageButton) findViewById(R.id.chat_btn_emote);
        mExpression = (LinearLayout) findViewById(R.id.expression);
        mFaceViewPager = (ViewPager) findViewById(R.id.face_viewpager);
        inputEdit = (EditText) findViewById(R.id.chat_edit_input);
        mShowHead = (RelativeLayout) findViewById(R.id.show_contact_head);


        mChatMsgList.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        contactName = intent.getStringExtra("contactName");
        mTitleBar.setTitleText(contactName + "");
//
//        handler = new Handler() {
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case 1:
//                        chatMessageAdapter.notifyDataSetChanged();
//                        mChatMsgList.scrollToPosition(chatList.size());
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//
//        ApplicationData.getInstance().setChatHandler(handler);
//        chatList = ApplicationData.getInstance().getChatMessagesMap()
//                .get(friendId);
//        if(chatList == null){
//
//            chatList = ImDB.getInstance(ContactChatActivity.this).getChatMessage(friendId);
//            ApplicationData.getInstance().getChatMessagesMap().put(friendId, chatList);
//        }

        chatMessageAdapter = new ChatMessageAdapter(R.layout.chat_message_item, chatList);
        mChatMsgList.setAdapter(chatMessageAdapter);

    }


    @Override
    protected void initListener() {
        super.initListener();
        //表情
        mEmoteBtn.setOnClickListener(this);
        mSendMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_send_msg:
                String content = inputEdit.getText().toString();
                chatList = new ArrayList<>();

                inputEdit.setText("");
                ChatEntity chatMessage = new ChatEntity();
                chatMessage.setContent(content);
                chatMessage.setSenderId(123);
                chatMessage.setReceiverId(friendId);
                chatMessage.setMessageType(ChatEntity.SEND);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd hh:mm:ss");
                String sendTime = sdf.format(date);
                chatMessage.setSendTime(sendTime);
                chatMessageAdapter.addData(chatMessage);
                Log.e("1111","size="+chatList.size());
                mChatMsgList.scrollToPosition(chatList.size());
//                UserAction.sendMessage(chatMessage);
//                ImDB.getInstance(ContactChatActivity.this)
//                        .saveChatMessage(chatMessage);
                mShowHead.setVisibility(View.INVISIBLE);

                break;
        }


    }
}
