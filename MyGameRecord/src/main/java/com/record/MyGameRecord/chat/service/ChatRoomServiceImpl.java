package com.record.MyGameRecord.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.record.MyGameRecord.chat.dao.ChatRoomDao;
import com.record.MyGameRecord.chat.vo.chatroom;

@Service("cService")
public class ChatRoomServiceImpl implements ChatRoomService{
	@Autowired
	ChatRoomDao cDao;

	@Override
	public int Chatroominsert(chatroom cr) {
		
		return cDao.Chatroominsert(cr);
	}

	@Override
	public chatroom Chatroomselect(String chatroomname) {
		
		return cDao.Chatroomselect(chatroomname);
	}
	
}
