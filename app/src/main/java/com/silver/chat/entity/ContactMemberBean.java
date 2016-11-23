package com.silver.chat.entity;

import android.widget.ImageView;

public class ContactMemberBean {

	private String contactName;    //显示的数据
	private ImageView contactHead;
	private String sortLetters;  //显示数据拼音的首字母

	public String getName() {
		return contactName;
	}
	public void setName(String name) {
		this.contactName = name;
	}
	
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
