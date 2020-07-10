package com.record.MyGameRecord.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class echoRoomHandler extends TextWebSocketHandler{
	
	// (<"bang_id", 방ID>, <"session", 세션>) - (<"bang_id", 방ID>, <"session", 세션>) - (<"bang_id", 방ID>, <"session", 세션>) 형태 
		private List<Map<String, Object>> sessionList = new ArrayList<Map<String, Object>>();
		
		private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
		
		//클라이언트와 연결 된 후
		@Override
		public void afterConnectionEstablished(WebSocketSession session) {
		//List 를 사용했으니..
			Map<String,Object> sessionmap = session.getAttributes();
			String chatRoomnumber = (String)sessionmap.get("chatRoomnumber");
			
			  Map<String, Object> map = new HashMap<String, Object>();
			  map.put("chatroom_no",chatRoomnumber);
			  map.put("session", session);
			
			  sessionList.add(map);
			  
			  System.out.println("제발.. : " +chatRoomnumber);
			
			
			  //세션값을 불러온 0번째 중괄호에 session.getId()를 넣으라는 뜻이다.
			  logger.info("{} 연결됨", session.getId()); 
			  //우리는 loginUser.getId()를 사용하면 되겠다..?
			
			
		}
	
		
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
			   
			   //이쪽부분때문에 메세지를 전송할떄마다 저장을해서 문제가 생긴다 이것을 고쳐주자1) override 클라이언트 연결될떄를 받아서 나눠주는법 2)if 로 예외처리하기
		
			   
			   for(int i=0; i<sessionList.size(); i++) {
					Map<String, Object> mapSessionList = sessionList.get(i);
					
					//sessionList에 담긴 Map에 값 가져옴 
					String chatroom_no = (String)mapSessionList.get("chatroom_no");
					WebSocketSession sess = (WebSocketSession)mapSessionList.get("session");

					//만약 Map값을 불러왔는데 방번호가 같다면?
					if(chatroom_no.equals(mapReceive.get("chatroom_no"))) {
//						Map<String, String> mapToSend = new HashMap<String, String>();
//						mapToSend.put("chatroom_no", chatroom_no);
//						mapToSend.put("msg", session.getId() + "" + mapReceive.get("msg"));
//					
//						System.out.println("확인" + mapToSend.get("chatroom_no"));
//						System.out.println("확인" + mapToSend.get("msg"));
						
//						//이쪽부분에서 session교체를 해주면 되지않을까? http와 socket 
						String jsonStr2 = chatroom_no + "|" +session.getId()+ "|" + mapReceive.get("msg");
						
//						String jsonStr = objectMapper.writeValueAsString(mapToSend);
						System.out.println("확인 에욱" + jsonStr2);
						sess.sendMessage(new TextMessage(jsonStr2));
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
