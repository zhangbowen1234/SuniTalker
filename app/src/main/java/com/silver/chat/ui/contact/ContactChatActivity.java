package com.silver.chat.ui.contact;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lqr.emoji.EmotionKeyboard;
import com.lqr.emoji.EmotionLayout;
import com.lqr.emoji.IEmotionExtClickListener;
import com.lqr.emoji.IEmotionSelectedListener;
import com.lqr.emoji.LQREmotionKit;
import com.lqr.emoji.MoonUtils;
import com.silver.chat.R;
import com.silver.chat.adapter.ChatMessageAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.databs.ImDB;
import com.silver.chat.entity.ApplicationData;
import com.silver.chat.entity.ChatEntity;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CircleImageView;
import com.silver.chat.view.TitleBarView;
import com.ssim.android.constant.SSMessageFormat;
import com.ssim.android.engine.SSEngine;
import com.ssim.android.listener.SSMessageSendListener;

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
    private ImageView mBack;

    int lastItemPosition;
    private EmotionLayout mElEmotion;
    private EmotionKeyboard mEmotionKeyboard;
    private RelativeLayout mLlContent;

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
        inputEdit = (EditText) findViewById(R.id.chat_edit_input);
        mShowHead = (RelativeLayout) findViewById(R.id.show_contact_head);
        mBack = (ImageView)findViewById(R.id.title_left_back);
        mElEmotion = (EmotionLayout) findViewById(R.id.elEmotion);
        mLlContent = (RelativeLayout) findViewById(R.id.rl_recyle_content);

        //设置管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mChatMsgList.setLayoutManager(linearLayoutManager);
        //将内容输入框交给EmotionLayout管理
        mElEmotion.attachEditText(inputEdit);
        //实现内容区与表情区仿微信切换效果
        initEmotionKeyboard();

        //判断是当前layoutManager是否为LinearLayoutManager
        // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
        //获取最后一个可见view的位置
//        lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
//        if (lastItemPosition!=-1)

    }


    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        contactName = intent.getStringExtra("contactName");
        mTitleBar.setTitleText(contactName + "");


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
//
//        chatMessageAdapter = new ChatMessageAdapter(R.layout.chat_message_item, chatList);
//        mChatMsgList.setAdapter(chatMessageAdapter);

    }


    @Override
    protected void initListener() {
        super.initListener();
        //表情
        mEmoteBtn.setOnClickListener(this);
        mSendMsg.setOnClickListener(this);
        mBack.setOnClickListener(this);
        //实现IEmotionSelectedListener接口，手动实现图文混排
//        mElEmotion.setEmotionSelectedListener(this);
        mElEmotion.setEmotionAddVisiable(true);
        mElEmotion.setEmotionSettingVisiable(true);
        mElEmotion.setEmotionExtClickListener(new IEmotionExtClickListener() {
            @Override
            public void onEmotionAddClick(View view) {
                ToastUtils.showMessage(mContext,"add");
            }

            @Override
            public void onEmotionSettingClick(View view) {
                ToastUtils.showMessage(mContext,"setting");
            }
        });
        //文字表情混合输入
        mElEmotion.setEmotionSelectedListener(new IEmotionSelectedListener() {
            @Override
            public void onEmojiSelected(String key) {
                if (inputEdit == null)
                    return;
                Editable editable = inputEdit.getText();
                if (key.equals("/DEL")) {
                    inputEdit.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                } else {
                    int start = inputEdit.getSelectionStart();
                    int end = inputEdit.getSelectionEnd();
                    start = (start < 0 ? 0 : start);
                    end = (start < 0 ? 0 : end);
                    editable.replace(start, end, key);

                    int editEnd = inputEdit.getSelectionEnd();
                    MoonUtils.replaceEmoticons(LQREmotionKit.getContext(), editable, 0, editable.toString().length());
                    inputEdit.setSelection(editEnd);
                }

            }

            @Override
            public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
                //得到贴图的存放位置
//                String stickerPath = LQREmotionKit.getStickerPath();
            }
        });
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
                Log.e("1111", "size=" + chatList.size());
                mChatMsgList.scrollToPosition(chatList.size());
//                UserAction.sendMessage(chatMessage);
//                ImDB.getInstance(ContactChatActivity.this)
//                        .saveChatMessage(chatMessage);
                mShowHead.setVisibility(View.INVISIBLE);

                mChatMsgList.smoothScrollToPosition(chatMessageAdapter.getItemCount()-1);
                SSEngine instance = SSEngine.getInstance();
//                instance.sendMessageToTargetId("5", SSMessageFormat.TEXT,"你好你好你好");

                instance.sendMessageToGroupId("291",SSMessageFormat.TEXT,"王晓阳，缺心眼");
                instance.setMsgSendListener(new SSMessageSendListener() {
                    @Override
                    public void didSend(boolean b, long l) {
                        Log.e(TAG,"didSend"+ "boolean:"+ b+";long:"+ l);
                    }
                });
                break;

            case R.id.chat_btn_emote:
                inputEdit.clearFocus();
                if (!mElEmotion.isShown()){
                    showEmotionLayout();
                    return ;
                }else if (mElEmotion.isShown()){
                    hideEmotionLayout();
                    return;
                }
                showEmotionLayout();
                break;
            case R.id.title_left_back:
                finish();
                break;
        }
    }


    private void showEmotionLayout() {
        mElEmotion.setVisibility(View.VISIBLE);
//        mEmoteBtn.setImageResource(R.mipmap.ic_cheat_keyboard);
    }

    private void hideEmotionLayout() {
//        mElEmotion.setVisibility(View.GONE);
        mEmoteBtn.setImageResource(R.drawable.ic_chat_emote_selected);
    }
    private void initEmotionKeyboard() {
        mEmotionKeyboard = EmotionKeyboard.with(this);
        mEmotionKeyboard.bindToContent(mLlContent);
        mEmotionKeyboard.bindToEmotionButton(mEmoteBtn);
//        mEmotionKeyboard.bindToEditText(inputEdit);
        mEmotionKeyboard.setEmotionLayout(mElEmotion);
    }
}
