//package com.silver.chat.ui.contact;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ListView;
//
//import com.silver.chat.R;
//import com.silver.chat.adapter.ChatMessageAdapter;
//import com.silver.chat.base.BaseActivity;
//import com.silver.chat.databs.ImDB;
//import com.silver.chat.entity.ApplicationData;
//import com.silver.chat.entity.ChatEntity;
//import com.silver.chat.view.TitleBarView;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//
//public class ChatActivity extends BaseActivity {
//	private TitleBarView mTitleBarView;
//	private int friendId;
//	private String friendName;
//	private ListView chatMeessageListView;
//	private ChatMessageAdapter chatMessageAdapter;
//	private Button sendButton;
//	private ImageButton emotionButton;
//	private EditText inputEdit;
//	private List<ChatEntity> chatList;
//	private Handler handler;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.activity_chat);
//		Intent intent = getIntent();
//		friendName = intent.getStringExtra("friendName");
//		friendId = intent.getIntExtra("friendId", 0);
//		initViews();
//		initEvents();
//	}
//
//	@Override
//	protected int getLayoutId() {
//		return 0;
//	}
//
//	protected void initViews() {
//		// TODO Auto-generated method stub
//		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
//		mTitleBarView.setCommonTitle(View.GONE, View.VISIBLE, View.GONE);
//		mTitleBarView.setTitleText("与" + friendName + "对话");
//		chatMeessageListView = (ListView) findViewById(R.id.chat_Listview);
//		sendButton = (Button) findViewById(R.id.chat_btn_send);
//		emotionButton = (ImageButton) findViewById(R.id.chat_btn_emote);
//		inputEdit = (EditText) findViewById(R.id.chat_edit_input);
//
//	}
//
//	protected void initEvents() {
//		handler = new Handler() {
//			public void handleMessage(Message msg) {
//				switch (msg.what) {
//				case 1:
//					chatMessageAdapter.notifyDataSetChanged();
//					chatMeessageListView.setSelection(chatList.size());
//					break;
//				default:
//					break;
//				}
//			}
//		};
//		ApplicationData.getInstance().setChatHandler(handler);
//		chatList = ApplicationData.getInstance().getChatMessagesMap()
//				.get(friendId);
//		if(chatList == null){
//			chatList = ImDB.getInstance(ChatActivity.this).getChatMessage(friendId);
//			ApplicationData.getInstance().getChatMessagesMap().put(friendId, chatList);
//		}
//		chatMessageAdapter = new ChatMessageAdapter(ChatActivity.this,chatList);
//		chatMeessageListView.setAdapter(chatMessageAdapter);
//		sendButton.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				String content = inputEdit.getText().toString();
//				inputEdit.setText("");
//				ChatEntity chatMessage = new ChatEntity();
//				chatMessage.setContent(content);
//				chatMessage.setSenderId(ApplicationData.getInstance()
//						.getUserInfo().getId());
//				chatMessage.setReceiverId(friendId);
//				chatMessage.setMessageType(ChatEntity.SEND);
//				Date date = new Date();
//				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd hh:mm:ss");
//				String sendTime = sdf.format(date);
//				chatMessage.setSendTime(sendTime);
//				chatList.add(chatMessage);
//				chatMessageAdapter.notifyDataSetChanged();
//				chatMeessageListView.setSelection(chatList.size());
//				UserAction.sendMessage(chatMessage);
//				ImDB.getInstance(ChatActivity.this)
//						.saveChatMessage(chatMessage);
//			}
//		});
//	}
//
//}
