package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lqr.emoji.EmotionKeyboard;
import com.lqr.emoji.EmotionLayout;
import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.ChatMessageAdapter;
import com.silver.chat.adapter.GroupChatMessageAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.ui.contact.ContactChatActivity;
import com.silver.chat.ui.contact.MyLocationActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CircleImageView;
import com.silver.chat.view.TitleBarView;
import com.silver.chat.view.recycleview.pulltorefreshable.WSRecyclerView;
import com.ssim.android.constant.SSMessageFormat;
import com.ssim.android.engine.SSEngine;
import com.ssim.android.listener.SSMessageSendListener;
import com.ssim.android.model.chat.SSGroupMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/6/1.
 */
public class GroupChatActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBarView titleBar;
    @BindView(R.id.round_three)
    ImageView roundThree;
    @BindView(R.id.round_two)
    ImageView roundTwo;
    @BindView(R.id.round_one)
    ImageView roundOne;
    @BindView(R.id.my_round_head_border)
    ImageView myRoundHeadBorder;
    @BindView(R.id.my_round_head)
    CircleImageView myRoundHead;
    @BindView(R.id.show_contact_head)
    RelativeLayout showContactHead;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recyle_content)
    WSRecyclerView recyleContent;
    @BindView(R.id.chat_btn_emote)
    ImageButton chatBtnEmote;
    @BindView(R.id.chat_edit_input)
    EditText chatEditInput;
    @BindView(R.id.chat_send_msg)
    ImageButton chatSendMsg;
    @BindView(R.id.chatInputHalving)
    TextView chatInputHalving;
    @BindView(R.id.elEmotion)
    EmotionLayout elEmotion;
    @BindView(R.id.chatLayoutMsg)
    LinearLayout chatLayoutMsg;
    @BindView(R.id.rl_recyle_content)
    RelativeLayout rlRecyleContent;
    @BindView(R.id.title_left_back)
    ImageView ivLeft;
    private SSGroupMessage ssGroupMessage;
    private EmotionKeyboard mEmotionKeyboard;
    private String userId;
    private String groupId;
    private long timeStamp;
    private GroupChatMessageAdapter chatMessageAdapter;
    private List<SSGroupMessage> groupMessageList = new ArrayList<>();
    private String groupName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_chat;
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        groupName = intent.getStringExtra("groupName");
        groupId = intent.getStringExtra("groupId");
        userId = PreferenceUtil.getInstance(this).getString(PreferenceUtil.USERID, "");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        recyleContent.setLayoutManager(linearLayoutManager);

        //将内容输入框交给EmotionLayout来处理
        elEmotion.attachEditText(chatEditInput);
        //实现内容区与表情区仿微信切换效果
        initEmotionKeyboard();
    }


    @Override
    protected void initData() {
        super.initData();

        titleBar.setTitleText(groupName + "");

        groupMessageList = AppContext.getInstance().instance.getGroupMessageList(userId, groupId, -1, 10);

        chatMessageAdapter = new GroupChatMessageAdapter(R.layout.chat_message_item, groupMessageList);
        if (groupMessageList.size() != 0) {
            showContactHead.setVisibility(View.INVISIBLE);
            //显示最后一条的聊天位置
            recyleContent.smoothScrollToPosition(chatMessageAdapter.getItemCount());
            chatMessageAdapter.notifyDataSetChanged();
        }
        //给recycleview设置适配器
        recyleContent.setAdapter(chatMessageAdapter);
        chatMessageAdapter.addHeaderView(recyleContent.getRefreshView());
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @OnClick({R.id.chat_btn_emote, R.id.chat_edit_input, R.id.chat_send_msg, R.id.chatInputHalving, R.id.elEmotion, R.id.chatLayoutMsg, R.id.rl_recyle_content,R.id.iv_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chat_btn_emote:
                break;
            case R.id.chat_edit_input:

                break;
            case R.id.chat_send_msg:
                String content = chatEditInput.getText().toString();
                if ("".equals(content) || content == null) {
                    ToastUtils.showMessage(mContext, "发送内容不能为空");
                } else {

                    ssGroupMessage = new SSGroupMessage();
                    //获取当前时间的时间戳
                    timeStamp = System.currentTimeMillis();
                    showContactHead.setVisibility(View.INVISIBLE);
                    chatEditInput.setText("");
                    ssGroupMessage.setContent(content);
                    ssGroupMessage.setSourceId(userId);
                    ssGroupMessage.setGroupId(groupId);
                    ssGroupMessage.setMessageTime(timeStamp);
                    groupMessageList.add(ssGroupMessage);
                    chatMessageAdapter.notifyDataSetChanged();
                    recyleContent.smoothScrollToPosition(chatMessageAdapter.getItemCount() - 1);

                    SSEngine instance = SSEngine.getInstance();
                    instance.sendMessageToGroupId(groupId, SSMessageFormat.TEXT, content);

                    instance.setMsgSendListener(new SSMessageSendListener() {
                        @Override
                        public void didSend(boolean b, long l) {

                        }
                    });
                }


                break;
            case R.id.chatInputHalving:
                break;
            case R.id.elEmotion:
                break;
            case R.id.chatLayoutMsg:
                break;
            case R.id.iv_location:
                Intent intent = new Intent(GroupChatActivity.this, MyLocationActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_recyle_content:
                break;

        }
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    private void initEmotionKeyboard() {
        mEmotionKeyboard = EmotionKeyboard.with(this);
        mEmotionKeyboard.bindToContent(rlRecyleContent);
        mEmotionKeyboard.bindToEmotionButton();
//        mEmotionKeyboard.bindToEditText(inputEdit);
        mEmotionKeyboard.setEmotionLayout(chatBtnEmote);
    }
}
