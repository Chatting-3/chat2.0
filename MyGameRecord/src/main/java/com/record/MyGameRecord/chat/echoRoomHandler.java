package com.record.MyGameRecord.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;


public class echoRoomHandler extends TextWebSocketHandler{
	
	// (<"bang_id", 방ID>, <"session", 세션>) - (<"bang_id", 방ID>, <"session", 세션>) - (<"bang_id", 방ID>, <"session", 세션>) 형태 
		private List<Map<String, Object>> sessionList = new ArrayList<Map<String, Object>>();
		
		private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
		
		
		//웹 소켓 서버로 데이터를 전송했을 경우
		 @Override
		 protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			  
			   logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
			 
			   //JSON --> MAP으로 변환
			   ObjectMapper objectMapper = new ObjectMapper();
			   Map<String, String> mapReceive = objectMapper.readValue(message.getPayload(), Map.class);
			   
			   Map<String, Object> map = new HashMap<String, Object>();
			   map.put("chatroom_no", mapReceive.get("chatroom_no"));
			   map.put("session", session);
			   System.out.println("한번더 더더더 확인" + session.getAttributes().get("userId"));
			   sessionList.add(map);
			   
			   
			   for(int i=0; i<sessionList.size(); i++) {
					Map<String, Object> mapSessionList = sessionList.get(i);
					
					String chatroom_no = (String)mapSessionList.get("chatroom_no");
					WebSocketSession sess = (WebSocketSession)mapSessionList.get("session");
					System.out.println("확인 마지막" + sess);
					
					if(chatroom_no.equals(mapReceive.get("chatroom_no"))) {
						Map<String, String> mapToSend = new HashMap<String, String>();
						mapToSend.put("chatroom_no", chatroom_no);
						mapToSend.put("msg", session.getId() + " | " + mapReceive.get("msg"));
					
						System.out.println("확인" + mapToSend.get("chatroom_no"));
						System.out.println("확인" + mapToSend.get("msg"));
						
						
						String jsonStr = objectMapper.writeValueAsString(mapToSend);
						System.out.println("확인 에욱" + jsonStr);
						sess.sendMessage(new TextMessage(jsonStr));
					}
			   }
			   
		   }
		
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
			//여기서 Db를 등록하면 되지않을까?
			//List 삭제
			ObjectMapper objectMapper = new ObjectMapper();
			String now_bang_id = "";
			logger.info("{} 연결 끊김",session.getId());
		
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> map = sessionList.get(i);
				String chatroom_no = (String)map.get("chatroom_no");
				WebSocketSession sess = (WebSocketSession)map.get("session");
				
				if(session.equals(sess)) {
					chatroom_no = chatroom_no;
					sessionList.remove(map);
					break;
				}	
		}
}
}
