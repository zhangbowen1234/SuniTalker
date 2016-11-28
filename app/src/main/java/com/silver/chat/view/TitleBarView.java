package com.silver.chat.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.util.SystemMethod;


/**
 * 自定义页面抬头
 */
public class TitleBarView extends RelativeLayout {

	private static final String TAG = "TitleBarView";
	private Context mContext;
	private ImageView mTitleBack,mTitleVideo,mTitleVoice;
	private TextView mChatName;
	public TitleBarView(Context context){
		super(context);
		mContext=context;
		initView();
	}
	
	public TitleBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext=context;
		initView();
	}
	
	private void initView(){
		LayoutInflater.from(mContext).inflate(R.layout.common_title_bar, this);
		mTitleBack=(ImageView) findViewById(R.id.title_left_back);
		mTitleVideo=(ImageView) findViewById(R.id.title_right_video);
		mTitleVoice=(ImageView) findViewById(R.id.title_right_voice);
		mChatName=(TextView) findViewById(R.id.title_name);
		
	}
	
	public void setCommonTitle(int LeftVisibility,int centerVisibility,int rightVisibility){
		mTitleBack.setVisibility(LeftVisibility);
		mTitleVideo.setVisibility(rightVisibility);
		mTitleVoice.setVisibility(rightVisibility);
		mChatName.setVisibility(centerVisibility);
		
	}
	
	public void setBtnLeft(int icon,int txtRes){
		Drawable img=mContext.getResources().getDrawable(icon);
		int height= SystemMethod.dip2px(mContext, 20);
		int width=img.getIntrinsicWidth()*height/img.getIntrinsicHeight();
		img.setBounds(0, 0, width, height);
//		mTitleBack.setText(txtRes);
//		mTitleBack.setCompoundDrawables(img, null, null, null);
		mTitleBack.setImageDrawable(img);
	}
	
	public void setBtnLeft(int txtRes){
//		mTitleBack.setText(txtRes);
	}
	
	
	public void setBtnRight(int icon){
		Drawable img=mContext.getResources().getDrawable(icon);
		int height=SystemMethod.dip2px(mContext, 30);
		int width=img.getIntrinsicWidth()*height/img.getIntrinsicHeight();
		img.setBounds(0, 0, width, height);
//		mTitleVideo.setCompoundDrawables(img, null, null, null);
		mTitleVideo.setImageDrawable(img);
	}
	

	
	@SuppressLint("NewApi")
	public void setPopWindow(PopupWindow mPopWindow,TitleBarView titleBaarView){
			mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E9E9E9")));
			mPopWindow.showAsDropDown(titleBaarView, 0,-15);
			mPopWindow.setAnimationStyle(R.style.popwin_anim_style);
			mPopWindow.setFocusable(true);
			mPopWindow.setOutsideTouchable(true);
			mPopWindow.update();
			
			setBtnRight(R.drawable.skin_conversation_title_right_btn_selected);
		}
	
	public void setTitleText(String txtRes){
		mChatName.setText(txtRes);
	}
	
	public void setBtnLeftOnclickListener(OnClickListener listener){
		mTitleVideo.setOnClickListener(listener);
	}
	
	public void setBtnRightOnclickListener(OnClickListener listener){
		mTitleVoice.setOnClickListener(listener);
	}
	
	public void destoryView(){
//		mTitleVideo.setText(null);
//		mTitleVoice.setText(null);
		mChatName.setText(null);
	}

}
