package com.silver.chat.ui.contact;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.silver.chat.R;
import com.silver.chat.adapter.ChatMessageAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.database.ChatEntity;
import com.silver.chat.util.CharacterParser;
import com.silver.chat.util.PinyinComparator;
import com.silver.chat.view.CircleImageView;
import com.silver.chat.view.TitleBarView;

import java.util.ArrayList;
import java.util.List;

public class ContactChatActivity extends BaseActivity {

    private CircleImageView mContactChatImg;
    private ImageButton mSendMsg;
    private String contactName;
    private TitleBarView mTitleBar;
    private RecyclerView mChatMsgList;
    private List<ChatEntity> chatList;
    private ChatMessageAdapter chatMessageAdapter;

//    private List<ContactMemberBean> SourceDateList;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

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


        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        //初始化联系人数据
        chatList = filledData(getResources().getStringArray(R.array.date));
    }
    /**
     * 为ListView填充数据
     *
     * @return
     * @params
     */
    private List<ChatEntity> filledData(String[] date) {
        List<ChatEntity> mSortList = new ArrayList<ChatEntity>();
        for (int i = 0; i < date.length; i++) {
            ChatEntity sortModel = new ChatEntity();
            sortModel.setContent(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            mSortList.add(sortModel);
        }
        return mSortList;
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


        mChatMsgList.setAdapter(new ChatMessageAdapter(R.layout.chat_message_item,chatList));

    }


    @Override
    protected void initListener() {
        super.initListener();

    }
}
