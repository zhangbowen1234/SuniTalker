package com.silver.chat.entity;

public class ContactMemberBean  {

	private String contactName;    //显示的数据
	private String sortLetters;  //显示数据拼音的首字母


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public String getContactName() {
		return contactName;
	}


	public String getSortLetters() {
		return sortLetters;
	}

	@Override
	public String toString() {
		return "ContactMemberBean{" +
				"contactName='" + contactName + '\'' +", sortLetters='" + sortLetters + '\'' +
				'}';
	}

}
