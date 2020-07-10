package com.record.MyGameRecord.chat.vo;

import java.io.Serializable;

public class chatroom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -563780966947488983L;
	
	private String chatroom_no;		//방번호
	private String chatroomname;	//방이름
	private String chatpassword;	//방비밀번호
	
	public chatroom() {
	}
	
	public String getChatroom_no() {
		return chatroom_no;
	}

	public void setChatroom_no(String chatroom_no) {
		this.chatroom_no = chatroom_no;
	}

	public chatroom(String chatroom_no, String chatroomname, String chatpassword) {
		this.chatroom_no = chatroom_no;
		this.chatroomname = chatroomname;
		this.chatpassword = chatpassword;
	}

	public chatroom(String chatroomname, String chatpassword) {
		this.chatroomname = chatroomname;
		this.chatpassword = chatpassword;
	}
	public String getChatroomname() {
		return chatroomname;
	}
	public void setChatroomname(String chatroomname) {
		this.chatroomname = chatroomname;
	}
	public String getChatpassword() {
		return chatpassword;
	}
	public void setChatpassword(String chatpassword) {
		this.chatpassword = chatpassword;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "chatroom [chatroom_no=" + chatroom_no + ", chatroomname=" + chatroomname + ", chatpassword="
				+ chatpassword + ", getChatroom_no()=" + getChatroom_no() + ", getChatroomname()=" + getChatroomname()
				+ ", getChatpassword()=" + getChatpassword() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	

}
