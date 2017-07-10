package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.GroupChatAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.entity.GroupMessageBean;
import com.silver.chat.ui.contact.MyLocationActivity;
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
import com.ssim.android.model.chat.SSGroupMessage;
import com.ssim.android.model.chat.SSLocation;
import com.ssim.android.model.chat.SSMessage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 2017/6/1.
 */
public class GroupChatActivity extends BaseActivity implements IEmotionSelectedListener,View.OnClickListener, SSMessageReceiveListener{
    private CircleImageView mContactChatImg;
    private ImageButton mSendMsg, mEmoteBtn;
    private EditText inputEdit;
    private TitleBarView mTitleBar;
    private WSRecyclerView mChatMsgList;
    private RelativeLayout mShowHead;
    private LinearLayout mLlContent;
    private ImageView mBack;
    private EmotionLayout mElEmotion;
    private EmotionKeyboard mEmotionKeyboard;
    private FrameLayout mFlEmotionView;

    //private SSGroupMessage ssGroupMessage;
    private String userId,groupId,groupName;
    private long timeStamp;
    //private GroupChatMessageAdapter chatMessageAdapter;
    private List<SSGroupMessage> groupMessageList = new ArrayList<>();
    private List<GroupMessageBean> groupMesList;
    private GroupChatAdapter groupChatAdapter;
    private GroupMessageBean groupMessageBean;
    private static final int REQUEST_CODE = 201;
    private boolean mIsFirst = false;
    private MyHandler mMyHandler;
    private SSGroupMessage receiveMsg = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_chat2;
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

        Intent intent = getIntent();
        groupName = intent.getStringExtra("groupName");
        groupId = intent.getStringExtra("groupId");
        userId = PreferenceUtil.getInstance(this).getString(PreferenceUtil.USERID, "");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        mChatMsgList.setLayoutManager(linearLayoutManager);

        //将内容输入框交给EmotionLayout来处理
        mElEmotion.attachEditText(inputEdit);
        //实现内容区与表情区仿微信切换效果
        initEmotionKeyboard();
        mMyHandler = new MyHandler(this);
    }


    @Override
    protected void initData() {
        super.initData();
        mTitleBar.setTitleText(groupName + "");
        groupMessageList = AppContext.getInstance().instance.getGroupMessageList(userId, groupId, -1, 10);
        resetBean(groupMessageList);
        groupChatAdapter = new GroupChatAdapter(groupMesList);
        //chatMessageAdapter = new GroupChatMessageAdapter(R.layout.chat_message_item, groupMesList);
        if (groupMessageList.size() != 0) {
            mShowHead.setVisibility(View.INVISIBLE);
            //显示最后一条的聊天位置
            mChatMsgList.smoothScrollToPosition(groupChatAdapter.getItemCount());
            groupChatAdapter.notifyDataSetChanged();
        }
        //给recycleview设置适配器
        mChatMsgList.setAdapter(groupChatAdapter);
        groupChatAdapter.addHeaderView(mChatMsgList.getRefreshView());
          /*刷新聊天记录暂时又bug*/
        if (groupMessageList == null || groupMessageList.size() == 0) {
            mChatMsgList.refreshComplete();
        } else {
            mChatMsgList.setOnRefreshCompleteListener(new WSRecyclerView.OnRefreshCompleteListener() {
                @Override
                public void onRefreshComplete() {
                    mMyHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            SSGroupMessage ssp2PMessage = groupMessageList.get((groupChatAdapter.getItemCount() - 1) - (groupChatAdapter.getItemCount() - 1));
//                            long messageTime = ssp2PMessage.getMessageTime();
//                            Log.e("aa", ssp2PMessage.getSourceId() + "/" + ssp2PMessage.getContent());
                            List<SSGroupMessage> p2PMsgList = SSEngine.getInstance().getGroupMessageList(userId, groupId, -1, 10);
                            groupMessageList.addAll((groupChatAdapter.getItemCount() - 1) - (groupChatAdapter.getItemCount() - 1), p2PMsgList);
                            resetBean(groupMessageList);
                            groupChatAdapter.setNewData(groupMesList);
                            groupChatAdapter.notifyDataSetChanged();
                            mChatMsgList.refreshComplete();
                        }
                    }, 1500);
                }
            });
        }
    }

    //条目展示用的RecycleView的Adapter是框架因为条目展示的泛型第一个参数时一个实体类需要继承BaseMulityItem，所以此处对bean重新封装一下
    private void resetBean(List<SSGroupMessage> groupMessageList) {
        groupMesList = new ArrayList<>();
        for (int i = 0; i < groupMessageList.size(); i++) {
            GroupMessageBean groupMessageBean = new GroupMessageBean(groupMessageList.get(i).getContentType());
            groupMessageBean.setMessageTime(groupMessageList.get(i).getMessageTime());
            groupMessageBean.setContent(groupMessageList.get(i).getContent());
            groupMessageBean.setContentType(groupMessageList.get(i).getContentType());
            groupMessageBean.setSourceId(groupMessageList.get(i).getSourceId());
            groupMesList.add(groupMessageBean);
        }
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
            timeStamp = System.currentTimeMillis();
            mShowHead.setVisibility(View.INVISIBLE);
            GroupMessageBean groupMessage = new GroupMessageBean(SSMessageFormat.LOCATION);
            groupMessage.setContent(address);
            groupMessage.setSourceId(userId);
            groupMessage.setGroupId(groupId);
            groupMessage.setContentType(SSMessageFormat.LOCATION);
            groupMessage.setMessageTime(timeStamp);
            groupMesList.add(groupMessage);
            groupChatAdapter.notifyDataSetChanged();
            mChatMsgList.smoothScrollToPosition(groupChatAdapter.getItemCount() - 1);
            SSLocation ssLocation = new SSLocation();
            ssLocation.address = address;
            ssLocation.latitude = latitude;
            ssLocation.longitude = longitude;
            String jsonLocation = ssLocation.toJson();
            SSEngine instance = SSEngine.getInstance();
            instance.sendMessageToGroupId(groupId, SSMessageFormat.LOCATION, jsonLocation);

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

    private void initEmotionKeyboard() {
        mEmotionKeyboard = EmotionKeyboard.with(this);
        mEmotionKeyboard.bindToContent(mLlContent);
        mEmotionKeyboard.bindToEmotionButton(mEmoteBtn);
        mEmotionKeyboard.bindToEditText(inputEdit);
        mEmotionKeyboard.setEmotionLayout(mFlEmotionView);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_send_msg:
                String content = inputEdit.getText().toString();
                if ("".equals(content) || content == null) {
                    ToastUtils.showMessage(mContext, "发送内容不能为空");
                } else {
                    groupMessageBean = new GroupMessageBean(SSMessageFormat.TEXT);
                    //获取当前时间的时间戳
                    timeStamp = System.currentTimeMillis();
                    mShowHead.setVisibility(View.INVISIBLE);
                    inputEdit.setText("");
                    groupMessageBean.setContent(content);
                    groupMessageBean.setSourceId(userId);
                    groupMessageBean.setGroupId(groupId);
                    groupMessageBean.setMessageTime(timeStamp);
                    groupMesList.add(groupMessageBean);

                    groupChatAdapter.notifyDataSetChanged();
                    mChatMsgList.smoothScrollToPosition(groupChatAdapter.getItemCount() - 1);

                    SSEngine instance = SSEngine.getInstance();
                    boolean isSend = instance.sendMessageToGroupId(groupId, SSMessageFormat.TEXT, content);
                    if (isSend) {
                        instance.setMsgSendListener(new SSMessageSendListener() {
                            @Override
                            public void didSend(boolean b, long l) {
                                if (!b) {
                                    ToastUtil.toastMessage(mContext, "服务器忙");
                                }
                            }
                        });
                    } else {
                        ToastUtil.toastMessage(mContext, "消息发送失败");
                    }
                }
                break;
            case R.id.iv_location1:
                Intent intent = new Intent(GroupChatActivity.this, MyLocationActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.title_left_back:
                finish();
                break;
        }
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
        if (ssMessage instanceof SSGroupMessage) {
            receiveMsg = (SSGroupMessage) ssMessage;
            String sourceId = receiveMsg.getGroupId();
            if (sourceId.equals(groupId) || sourceId == groupId) {
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
                        groupMessageList.add(receiveMsg);
                        resetBean(groupMessageList);
                        groupChatAdapter.setNewData(groupMesList);
                        groupChatAdapter.notifyDataSetChanged();
                        mChatMsgList.smoothScrollToPosition(groupChatAdapter.getItemCount() - 1);
                    }
                    break;
            }
        }
    };
    private static class MyHandler extends Handler {
        private WeakReference<GroupChatActivity> activityWeakReference;

        public MyHandler(GroupChatActivity fragment) {
            activityWeakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            GroupChatActivity fragment = activityWeakReference.get();
            if (fragment == null) {
                return;
            }
        }
    }
}
