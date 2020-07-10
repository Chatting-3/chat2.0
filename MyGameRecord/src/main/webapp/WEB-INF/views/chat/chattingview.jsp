<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>

</head>
<body>
	<h1>Chatting Page (id: ${userId})</h1>
<div>
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="전송"/>
	<div id="chatdata">
		<input type="hidden" value="${userId }" id="sessionuserid">
	</div>
	</div>
</body>
<script type="text/javascript">
	let sock = new SockJS("<c:url value="/echo"/>");
	//websocket 서버에서 메시지를 보내면 자동으로 실행된다.
	sock.onmessage = onMessage;
	//websocket 과 연결을 끊고 싶을때 실행하는 메소드
	sock.onclose = onClose;

	$(function(){
		$("#sendBtn").click(function(){
	        sendMessage();
			console.log('send message...');
	    });
	});
	        
	function sendMessage(){      
		//websocket으로 메시지를 보내겠다.
	  	sock.send($("#message").val());     
	}
	            
	//evt 파라미터는 websocket이 보내준 데이터다.
	function onMessage(evt){  //변수 안에 function자체를 넣음.
		var data = evt.data;
		var sessionid = null;
		var message = null;
		
		//문자열을 splite//
		 var strArray = data.split('|'); 
		
		for(var i=0; i<strArray.length; i++){
			console.log('str['+i+']: ' + strArray[i]);
		}
		
		//current session id//
		var currentuser_session = $("#sessionuserid").val();
		console.log('current session id: ' + currentuser_session);
		
		sessionid = strArray[0]; //현재 메세지를 보낸 사람의 세션 등록//
		message = strArray[1]; //현재 메세지를 저장//
		
		//나와 상대방이 보낸 메세지를 구분하여 영역을 나눈다.//
		if(sessionid == currentuser_session){
			var printHTML = "<div>";
				printHTML += "<div>";
				printHTML += "<strong>["+sessionid +"] -> "+message+"</strong>";
				printHTML += "</div>";
				printHTML += "</div>";
			
			$("#chatdata").append(printHTML);
		} else{
			var printHTML = "<div>";
			printHTML += "<div>";
			printHTML += "<strong>["+sessionid+"] -> "+message+"</strong>";
			printHTML += "</div>";
			printHTML += "</div>";
			
			$("#chatdata").append(printHTML);
		}
		
		console.log('chatting data: ' + data);
		
	  	/* sock.close(); */
	}
	    
	function onClose(evt){
		$("#data").append("연결 끊김");
	}  
</script>


</html>