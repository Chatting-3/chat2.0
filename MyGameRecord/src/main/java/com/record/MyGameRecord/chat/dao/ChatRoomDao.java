package com.record.MyGameRecord.chat.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.record.MyGameRecord.chat.vo.chatroom;

@Repository("cDao")
public class ChatRoomDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int Chatroominsert(chatroom cr) {
	
		return sqlSessionTemplate.insert("chatMapper.insertChatRoom", cr);
	}

	public chatroom Chatroomselect(String chatroomname) {
		
		return sqlSessionTemplate.selectOne("chatMapper.selectChatRoomOne", chatroomname);
	}

}
