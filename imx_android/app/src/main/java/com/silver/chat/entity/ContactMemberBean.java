package com.silver.chat.entity;

public class ContactMemberBean  {

	private String nickName;    //显示的数据
	private String sortLetters;  //显示数据拼音的首字母


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public String getNickName() {
		return nickName;
	}


	public String getSortLetters() {
		return sortLetters;
	}

	@Override
	public String toString() {
		return "ContactMemberBean{" +
				"nickName='" + nickName + '\'' +", sortLetters='" + sortLetters + '\'' +
				'}';
	}

}
