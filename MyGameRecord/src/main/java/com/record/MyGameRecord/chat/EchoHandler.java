package com.record.MyGameRecord.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;



public class EchoHandler extends TextWebSocketHandler{
		
	//List 사용
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	
	//클라이언트와 연결 된 후
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
	//List 를 사용했으니..
		System.out.println("session uri? : "+ session.toString());
		sessionList.add(session);	
	//세션값을 불러온 0번째 중괄호에 session.getId()를 넣으라는 뜻이다.
		logger.info("{} 연결됨", session.getId()); 
		//우리는 loginUser.getId()를 사용하면 되겠다..?
		
		
	}
	
	//웹 소켓 서버로 데이터를 전송했을 경우
	 @Override
	 protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		  
		   logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
		   
		   Map<String,Object> map = session.getAttributes();
		   String userId = (String)map.get("userId");
		   System.out.println("로그인 한 아이디 : " + userId);


		   for(WebSocketSession sess : sessionList){
	        	sess.sendMessage(new TextMessage(userId+ "|" + message.getPayload()));
	        }
	   }
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		//여기서 Db를 등록하면 되지않을까?
		//List 삭제
		sessionList.remove(session);	
		logger.info("{} 연결 끊김",session.getId());
		 System.out.println("채팅방 퇴장자: " + session.getPrincipal().getName());
	}
	
}
