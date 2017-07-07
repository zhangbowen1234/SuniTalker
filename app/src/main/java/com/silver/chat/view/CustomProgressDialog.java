package com.silver.chat.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.silver.chat.R;


/**
 * 加载进度条
 * Created by hibon on 2017
 */
public class CustomProgressDialog extends ProgressDialog {
	private Context mContext;
	private String mLoadingTip;
	private TextView mLoadingTv;

	public CustomProgressDialog(Context context, String content, int theme) {
		super(context, theme);
		this.mContext = context;
		this.mLoadingTip = content;
		// 设置不可点击取消
		setCanceledOnTouchOutside(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_load);
		mLoadingTv = (TextView) findViewById(R.id.txt_loading);
		mLoadingTv.setText(mLoadingTip);
	}

	public void setContent(String str) {
		mLoadingTv.setText(str);
	}
}
