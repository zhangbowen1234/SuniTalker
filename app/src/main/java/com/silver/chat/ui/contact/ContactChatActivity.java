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
import com.silver.chat.entity.ChatMessageBean;
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
import com.ssim.android.model.chat.SSLocation;
import com.ssim.android.model.chat.SSMessage;
import com.ssim.android.model.chat.SSP2PMessage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
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
    private LinearLayout mLlContent,mLl_title_name;
    private String friendId, userId, chatType;
    private ImageView mBack, ivLocation;
    private EmotionLayout mElEmotion;
    private EmotionKeyboard mEmotionKeyboard;
    private FrameLayout mFlEmotionView;

    private SSP2PMessage mChatMessage;
    private long timestamp;
    private List<SSP2PMessage> p2PMessageList;
    private List<ChatMessageBean> chatMessageList  = new ArrayList<>();
    private ChatMessageBean chatMessageBean;
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
        chatMessageAdapter.notifyDataSetChanged();
        mChatMsgList.setAdapter(chatMessageAdapter);
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
        mLl_title_name = (LinearLayout) findViewById(R.id.ll_title_name);
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
        p2PMessageList = SSEngine.getInstance().getP2PMessageList(friendId, -1, 10);
        resetBean(p2PMessageList);
        chatMessageAdapter = new ChatMessageAdapter(chatMessageList, userAvatar);
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
//                            SSP2PMessage ssp2PMessage = p2PMessageList.get((chatMessageAdapter.getItemCount() - 1) - (chatMessageAdapter.getItemCount() - 1));
//                            long messageTime = ssp2PMessage.getMessageTime();
//                            Log.e("aa", ssp2PMessage.getSourceId() + "/" + ssp2PMessage.getContent());
//                            List<SSP2PMessage> p2PMsgList = SSEngine.getInstance().getP2PMessageList(userId, friendId, messageTime, 10);
//                            p2PMessageList.addAll((chatMessageAdapter.getItemCount() - 1) - (chatMessageAdapter.getItemCount() - 1), p2PMsgList);
//                            resetBean(p2PMessageList);
//                            chatMessageAdapter.setNewData(chatMessageList);
//                            chatMessageAdapter.notifyDataSetChanged();
                            mChatMsgList.refreshComplete();
                        }
                    }, 1500);
                }
            });
        }
    }

    //条目展示用的RecycleView的Adapter是框架因为条目展示的泛型第一个参数时一个实体类需要继承BaseMulityItem，所以此处对bean重新封装一下
    private void resetBean(List<SSP2PMessage> MessageList) {

        for (int i = 0; i < MessageList.size(); i++) {
            chatMessageBean = new ChatMessageBean(MessageList.get(i).getContentType());
            chatMessageBean.setMessageTime(MessageList.get(i).getMessageTime());
            chatMessageBean.setContent(MessageList.get(i).getContent());
            chatMessageBean.setContentType(MessageList.get(i).getContentType());
            chatMessageBean.setSourceId(MessageList.get(i).getSourceId());
            chatMessageList.add(chatMessageBean);
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
        mLl_title_name.setOnClickListener(this);
        /*实现IEmotionSelectedListener接口，手动实现图文混排*/
        mElEmotion.setEmotionAddVisiable(true);
        mElEmotion.setEmotionSettingVisiable(true);
        mElEmotion.setEmotionSelectedListener(this);
        //开启定位信息界面
        ivLocation.setOnClickListener(this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String address = data.getStringExtra("address");
            float longitude = data.getFloatExtra("longitude", 0);
            float latitude = data.getFloatExtra("latitude", 0);
            ToastUtil.toastMessage(mContext, address + longitude + "   " + latitude);
            //获取当前时间的时间戳
            timestamp = System.currentTimeMillis();
            mShowHead.setVisibility(View.INVISIBLE);
            ChatMessageBean userMessage = new ChatMessageBean(SSMessageFormat.LOCATION);
            userMessage.setContent(address);
            userMessage.setSourceId(userId);
            userMessage.setContentType(SSMessageFormat.LOCATION);
            userMessage.setMessageTime(timestamp);
            chatMessageList.add(userMessage);
            chatMessageAdapter.notifyDataSetChanged();
            mChatMsgList.smoothScrollToPosition(chatMessageAdapter.getItemCount() - 1);
            SSLocation ssLocation = new SSLocation();
            ssLocation.address = address;
            ssLocation.latitude = latitude;
            ssLocation.longitude = longitude;
            String jsonLocation = ssLocation.toJson();
            SSEngine instance = SSEngine.getInstance();
            instance.sendMessageToTargetId(friendId, SSMessageFormat.LOCATION, jsonLocation);

            instance.setMsgSendListener(new SSMessageSendListener() {
                @Override
                public void didSend(boolean b, long l) {
                    if (!b) {
                        ToastUtil.toastMessage(mContext, "发送失败");
                    }
                }
            });
        }
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
                    chatMessageBean = new ChatMessageBean(SSMessageFormat.TEXT);
                    chatMessageBean.setContent(editcontent);
                    chatMessageBean.setSourceId(userId);
                    chatMessageBean.setTargetId(friendId);
                    chatMessageBean.setMessageTime(timestamp);
                    chatMessageList.add(chatMessageBean);
                    chatMessageAdapter.notifyDataSetChanged();
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
            case R.id.iv_location:
                Intent intent = new Intent(ContactChatActivity.this, MyLocationActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.title_left_back:
                finish();
                break;
            case R.id.ll_title_name:

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
        String stickerPath = stickerBitmapPath;
        //发送图片
    }

    @Override
    public void receiveMsg(SSMessage ssMessage) {
        if (ssMessage instanceof SSP2PMessage) {
            receiveMsg = (SSP2PMessage) ssMessage;
            Log.e("receiveMsg1",receiveMsg.getContent());
            String sourceId = receiveMsg.getSourceId();
            if (sourceId.equals(friendId) || sourceId == friendId) {
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
//                        p2PMessageList.add(receiveMsg);
                        chatMessageBean = new ChatMessageBean(receiveMsg.getContentType());
                        chatMessageBean.setMessageTime(receiveMsg.getMessageTime());
                        chatMessageBean.setContent(receiveMsg.getContent());
                        chatMessageBean.setContentType(receiveMsg.getContentType());
                        chatMessageBean.setSourceId(receiveMsg.getSourceId());
                        chatMessageList.add(chatMessageBean);
                        chatMessageAdapter.setNewData(chatMessageList);
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
