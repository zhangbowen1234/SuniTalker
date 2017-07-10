package com.silver.chat.ui.contact;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lqr.emoji.EmotionKeyboard;
import com.lqr.emoji.EmotionLayout;
import com.lqr.emoji.IEmotionExtClickListener;
import com.lqr.emoji.IEmotionSelectedListener;
import com.silver.chat.R;
import com.silver.chat.adapter.ChatMessageAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CircleImageView;
import com.silver.chat.view.TitleBarView;
import com.silver.chat.view.recycleview.pulltorefreshable.WSRecyclerView;
import com.ssim.android.constant.SSMessageFormat;
import com.ssim.android.engine.SSEngine;
import com.ssim.android.listener.SSMessageReceiveListener;
import com.ssim.android.listener.SSMessageSendListener;
import com.ssim.android.model.chat.SSMessage;
import com.ssim.android.model.chat.SSP2PMessage;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 聊天界面
 * Created by hibon on 2017
 */
public class ContactChatActivity extends BaseActivity implements IEmotionSelectedListener, View.OnClickListener, SSMessageReceiveListener {

    private static final int REQUEST_CODE = 200;
    private CircleImageView mContactChatImg;
    private ImageButton mSendMsg, mEmoteBtn;
    private EditText inputEdit;
    private TitleBarView mTitleBar;
    private WSRecyclerView mChatMsgList;
    private RelativeLayout mShowHead;
    private ChatMessageAdapter chatMessageAdapter;
    private ViewPager mFaceViewPager;
    private LinearLayout mLlContent;
    private String friendId, userId, chatType;
    private ImageView mBack, ivLocation;
    private EmotionLayout mElEmotion;
    private EmotionKeyboard mEmotionKeyboard;
    private FrameLayout mFlEmotionView;
    private SSP2PMessage mChatMessage;
    private long timestamp;
    private List<SSP2PMessage> p2PMessageList;
    private MyHandler mMyHandler;
    private String editcontent, contactName;
    private String userAvatar;
    private SSP2PMessage receiveMsg = null;
    private boolean mIsFirst = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_chat;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mIsFirst) {
            inputEdit.clearFocus();
        } else {
            mIsFirst = false;
        }
    }

    @Override
    protected void initView() {
        super.initView();
        mContactChatImg = (CircleImageView) findViewById(R.id.my_round_head);
        mSendMsg = (ImageButton) findViewById(R.id.chat_send_msg);
        mTitleBar = (TitleBarView) findViewById(R.id.title_bar);
        mChatMsgList = (WSRecyclerView) findViewById(R.id.recyle_content);
        mEmoteBtn = (ImageButton) findViewById(R.id.chat_btn_emote);
        inputEdit = (EditText) findViewById(R.id.chat_edit_input);
        mShowHead = (RelativeLayout) findViewById(R.id.show_contact_head);
        mFlEmotionView = (FrameLayout) findViewById(R.id.flEmotionView);
        mBack = (ImageView) findViewById(R.id.title_left_back);
        mElEmotion = (EmotionLayout) findViewById(R.id.elEmotion);
        mLlContent = (LinearLayout) findViewById(R.id.llContent);
        ivLocation = (ImageView) findViewById(R.id.iv_location);
        mChatMessage = new SSP2PMessage();
        /*设置管理*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        mChatMsgList.setLayoutManager(linearLayoutManager);
        /*将内容输入框交给EmotionLayout管理*/
        mElEmotion.attachEditText(inputEdit);
        /*实现内容区与表情区仿微信切换效果*/
        initEmotionKeyboard();
        mMyHandler = new MyHandler(this);

    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        contactName = intent.getStringExtra("contactName");
        friendId = intent.getStringExtra("friendId");
        chatType = intent.getStringExtra("chatType");
        userAvatar = intent.getStringExtra("userAvatar");
        mTitleBar.setTitleText(contactName + "");
        userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");
        /*获取当前系统时间的13位的时间戳*/
        timestamp = System.currentTimeMillis();
        /*
         * 私人聊天列表
         */
        p2PMessageList = SSEngine.getInstance().getP2PMessageList(userId, friendId, -1, 10);
        chatMessageAdapter = new ChatMessageAdapter(R.layout.chat_message_item, p2PMessageList, userAvatar);
        if (p2PMessageList.size() != 0) {
            mShowHead.setVisibility(View.INVISIBLE);
            /*显示聊天显示最后一条的位置*/
            mChatMsgList.smoothScrollToPosition(chatMessageAdapter.getItemCount());
            chatMessageAdapter.notifyDataSetChanged();
        }
         /*给RecyclerView列表设置适配器*/
        mChatMsgList.setAdapter(chatMessageAdapter);
        chatMessageAdapter.addHeaderView(mChatMsgList.getRefreshView());
         /*刷新聊天记录*/
        if (p2PMessageList == null || p2PMessageList.size() == 0) {
            mChatMsgList.refreshComplete();
        } else {
            mChatMsgList.setOnRefreshCompleteListener(new WSRecyclerView.OnRefreshCompleteListener() {
                @Override
                public void onRefreshComplete() {
                    mMyHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SSP2PMessage ssp2PMessage = p2PMessageList.get((chatMessageAdapter.getItemCount() - 1) - (chatMessageAdapter.getItemCount() - 1));
                            long messageTime = ssp2PMessage.getMessageTime();
                            Log.e("aa", ssp2PMessage.getSourceId() + "/" + ssp2PMessage.getContent());
                            List<SSP2PMessage> p2PMsgList = SSEngine.getInstance().getP2PMessageList(userId, friendId, messageTime, 10);
                            p2PMessageList.addAll((chatMessageAdapter.getItemCount() - 1) - (chatMessageAdapter.getItemCount() - 1), p2PMsgList);
                            chatMessageAdapter.notifyDataSetChanged();
                            mChatMsgList.refreshComplete();
                        }
                    }, 1500);
                }
            });
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        /*收到消息监听*/
        SSEngine.getInstance().setMsgRcvListener(this);
        /*表情*/
        mSendMsg.setOnClickListener(this);
        mBack.setOnClickListener(this);
        /*实现IEmotionSelectedListener接口，手动实现图文混排*/
        mElEmotion.setEmotionAddVisiable(true);
        mElEmotion.setEmotionSettingVisiable(true);
        mElEmotion.setEmotionSelectedListener(this);
        //开启定位信息界面
        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactChatActivity.this, MyLocationActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        mElEmotion.setEmotionExtClickListener(new IEmotionExtClickListener() {
            @Override
            public void onEmotionAddClick(View view) {
                ToastUtils.showMessage(mContext, "add");
            }

            @Override
            public void onEmotionSettingClick(View view) {
                ToastUtils.showMessage(mContext, "setting");
            }
        });

        mChatMsgList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //隐藏软键盘
                mEmoteBtn.setImageResource(R.drawable.ic_chat_emote_normal);
                mEmotionKeyboard.hideSoftInput();
                closeBottomAndKeyboard();
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_send_msg:
                editcontent = inputEdit.getText().toString();
                if ("".equals(editcontent) || editcontent == null) {
                    ToastUtils.showMessage(mContext, "没有发送的内容");
                } else {
                    inputEdit.setText("");
                    mChatMessage.setContent(editcontent);
                    mChatMessage.setSourceId(userId);
                    mChatMessage.setTargetId(friendId);
                    mChatMessage.setMessageTime(timestamp);
                    p2PMessageList.add(mChatMessage);
                    mChatMsgList.scrollToPosition(p2PMessageList.size());
                    mShowHead.setVisibility(View.INVISIBLE);
                    mChatMsgList.smoothScrollToPosition(chatMessageAdapter.getItemCount() - 1);

                    SSEngine instance = SSEngine.getInstance();
                    boolean isSend = instance.sendMessageToTargetId(friendId, SSMessageFormat.TEXT, editcontent);
                    if (isSend) {
                        instance.setMsgSendListener(new SSMessageSendListener() {
                            @Override
                            public void didSend(boolean b, long l) {
                                if (!b) {
                                    ToastUtil.toastMessage(mContext, "服务器忙");
                                }else {
                                    Log.e("didSend",b+"");
                                }
                            }
                        });
                    } else {
                        ToastUtil.toastMessage(mContext, "消息发送失败");
                    }
                }
                break;
            case R.id.title_left_back:
                finish();
                break;
        }
    }

    private void initEmotionKeyboard() {
        mEmotionKeyboard = EmotionKeyboard.with(this);
        mEmotionKeyboard.bindToEditText(inputEdit);
        mEmotionKeyboard.bindToContent(mLlContent);
        mEmotionKeyboard.setEmotionLayout(mFlEmotionView);
        mEmotionKeyboard.bindToEmotionButton(mEmoteBtn);
        mEmotionKeyboard.setOnEmotionButtonOnClickListener(new EmotionKeyboard.OnEmotionButtonOnClickListener() {
            @Override
            public boolean onEmotionButtonOnClickListener(View view) {
                switch (view.getId()) {
                    case R.id.chat_btn_emote:
                        if (!mElEmotion.isShown()) {
                            showEmotionLayout();
                        } else if (mElEmotion.isShown()) {
                            mEmoteBtn.setImageResource(R.drawable.ic_chat_emote);
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void showEmotionLayout() {
        mElEmotion.setVisibility(View.VISIBLE);
        mEmoteBtn.setImageResource(R.drawable.ic_chat_emote_selected);
    }

    private void hideEmotionLayout() {
        mElEmotion.setVisibility(View.GONE);
        mEmoteBtn.setImageResource(R.drawable.ic_chat_emote);
    }

    private void closeBottomAndKeyboard() {
        mElEmotion.setVisibility(View.GONE);
        if (mEmotionKeyboard != null) {
            mEmotionKeyboard.interceptBackPress();
        }
    }

    @Override
    public void onBackPressed() {
        if (mElEmotion.isShown()) {
            mEmotionKeyboard.interceptBackPress();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onEmojiSelected(String key) {
        Log.e("Sun", "onEmojiSelected : " + key);
    }

    @Override
    public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
        Toast.makeText(getApplicationContext(), stickerBitmapPath, Toast.LENGTH_SHORT).show();
        Log.e("Sun", "stickerBitmapPath : " + stickerBitmapPath);
    }

    @Override
    public void receiveMsg(SSMessage ssMessage) {
        if (ssMessage instanceof SSP2PMessage) {
            receiveMsg = (SSP2PMessage) ssMessage;
            Log.e("receiveMsg",receiveMsg.getContent());
            String sourceId = receiveMsg.getSourceId();
            if (sourceId.equals(friendId) || sourceId == friendId) {
                Log.e(TAG, receiveMsg.getContent());
                mHandler.sendEmptyMessage(0);
            }
        }
    }

    /**
     * 处理新收到的消息
     */
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    /*显示新收到的消息*/
                    if (receiveMsg != null) {
                        mShowHead.setVisibility(View.INVISIBLE);
                        p2PMessageList.add(receiveMsg);
                        chatMessageAdapter.setNewData(p2PMessageList);
                        chatMessageAdapter.notifyDataSetChanged();
                        mChatMsgList.smoothScrollToPosition(chatMessageAdapter.getItemCount() - 1);
                    }
                    break;
            }
        }
    };

    private static class MyHandler extends Handler {
        private WeakReference<ContactChatActivity> activityWeakReference;

        public MyHandler(ContactChatActivity fragment) {
            activityWeakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ContactChatActivity fragment = activityWeakReference.get();
            if (fragment == null) {
                return;
            }
        }
    }


}
