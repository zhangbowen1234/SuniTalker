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
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.view.CircleImageView;
import com.silver.chat.view.TitleBarView;
import com.ssim.android.constant.SSMessageFormat;
import com.ssim.android.engine.SSEngine;
import com.ssim.android.listener.SSMessageSendListener;
import com.ssim.android.model.chat.SSGroupMessage;

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
    RecyclerView recyleContent;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_chat;
    }

    @Override
    protected void initView() {
        super.initView();
        //List<SSGroupMessage> groupMessageList = AppContext.getInstance().instance.getGroupMessageList("8", "233", 0, 3);
        ssGroupMessage = new SSGroupMessage();
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
        Intent intent = getIntent();
        String groupName = intent.getStringExtra("groupName");
        groupId = intent.getStringExtra("groupId");
        titleBar.setTitleText(groupName+"");
        //List<SSGroupMessage> groupMessageList = AppContext.getInstance().instance.getGroupMessageList(userId, groupId, -1, 10);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    @OnClick({R.id.chat_btn_emote, R.id.chat_edit_input, R.id.chat_send_msg, R.id.chatInputHalving, R.id.elEmotion, R.id.chatLayoutMsg, R.id.rl_recyle_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chat_btn_emote:
                break;
            case R.id.chat_edit_input:
                break;
            case R.id.chat_send_msg:
                String content = chatEditInput.getText().toString();
                chatEditInput.setText("");
                ssGroupMessage.setContent(content);
                ssGroupMessage.setSourceId(userId);
               /* ssGroupMessage.setTargetId(friendId);
                ssGroupMessage.setMessageTime(timestamp);
                chatMessageAdapter.addData(mChatMessage);
                mChatMsgList.scrollToPosition(p2PMessageList.size());
                mShowHead.setVisibility(View.INVISIBLE);
                mChatMsgList.smoothScrollToPosition(chatMessageAdapter.getItemCount() - 1);*/
                SSEngine instance = SSEngine.getInstance();
                instance.sendMessageToGroupId(groupId,SSMessageFormat.TEXT,content);
                instance.setMsgSendListener(new SSMessageSendListener() {
                    @Override
                    public void didSend(boolean b, long l) {
                        Log.e(TAG, "didSend" + "boolean:" + b + ";long:" + l);
                    }
                });
                break;
            case R.id.chatInputHalving:
                break;
            case R.id.elEmotion:
                break;
            case R.id.chatLayoutMsg:
                break;
            case R.id.rl_recyle_content:
                break;

        }
    }
    private void initEmotionKeyboard() {
        mEmotionKeyboard = EmotionKeyboard.with(this);
        mEmotionKeyboard.bindToContent(rlRecyleContent);
        mEmotionKeyboard.bindToEmotionButton();
//        mEmotionKeyboard.bindToEditText(inputEdit);
        mEmotionKeyboard.setEmotionLayout(chatBtnEmote);
    }
}
