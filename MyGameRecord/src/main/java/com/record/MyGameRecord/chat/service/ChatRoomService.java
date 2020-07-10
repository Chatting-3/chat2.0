package com.record.MyGameRecord.chat.service;

import com.record.MyGameRecord.chat.vo.chatroom;

public interface ChatRoomService {

	int Chatroominsert(chatroom cr);

	chatroom Chatroomselect(String chatroomname);

}
