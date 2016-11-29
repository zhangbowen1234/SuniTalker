package com.silver.chat.ui.contact;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.silver.chat.R;
import com.silver.chat.adapter.ChatMessageAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.database.ChatEntity;
import com.silver.chat.view.CircleImageView;
import com.silver.chat.view.TitleBarView;

import java.util.List;

//                       .::::.
//                     .::::::::.
//                    :::::::::::
//                 ..:::::::::::'
//              '::::::::::::'
//                .::::::::::
//           '::::::::::::::..
//                ..::::::::::::.
//              ``::::::::::::::::
//               ::::``:::::::::'        .:::.
//              ::::'   ':::::'       .::::::::.
//            .::::'      ::::     .:::::::'::::.
//           .:::'       :::::  .:::::::::' ':::::.
//          .::'        :::::.:::::::::'      ':::::.
//         .::'         ::::::::::::::'         ``::::.
//     ...:::           ::::::::::::'              ``::.
//    ```` ':.          ':::::::::'                  ::::..
//                       '.:::::'                    ':'````..

public class ContactChatActivity extends BaseActivity {

    private CircleImageView mContactChatImg;
    private Button mSendMsg;
    private String contactName;
    private TitleBarView mTitleBar;
    private RecyclerView mChatMsgList;
    private List<ChatEntity> chatList;
    private ChatMessageAdapter chatMessageAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_chat;
    }


    @Override
    protected void initView() {
        super.initView();

        mContactChatImg = (CircleImageView) findViewById(R.id.contact_chat_img);
        mSendMsg = (Button) findViewById(R.id.chat_send_msg);
        mTitleBar = (TitleBarView) findViewById(R.id.title_bar);
        mChatMsgList = (RecyclerView)findViewById(R.id.recyle_content);




    }


    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        contactName = intent.getStringExtra("contactName");
        mTitleBar.setTitleText(contactName + "");


//        ApplicationData.getInstance().setChatHandler(handler);
//        chatList = ApplicationData.getInstance().getChatMessagesMap()
//                .get(friendId);
//        if(chatList == null){
//            chatList = ImDB.getInstance(ContactChatActivity.this).getChatMessage(friendId);
//            ApplicationData.getInstance().getChatMessagesMap().put(friendId, chatList);
//        }
        chatMessageAdapter = new ChatMessageAdapter(R.layout.chat_message_item,chatList);
        mChatMsgList.setAdapter(chatMessageAdapter);

    }


    @Override
    protected void initListener() {
        super.initListener();

    }
}
