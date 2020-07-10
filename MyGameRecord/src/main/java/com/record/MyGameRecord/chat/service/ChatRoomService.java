package com.record.MyGameRecord.chat.service;

import java.util.ArrayList;

import com.record.MyGameRecord.chat.vo.chatroom;

public interface ChatRoomService {

	int Chatroominsert(chatroom cr);

	chatroom Chatroomselect(String chatroomname);

	ArrayList<chatroom> selectList();



}
