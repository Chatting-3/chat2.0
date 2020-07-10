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
<h1>${loginUser.name }님 ${ch.chatroom_no }방에 오신것을 환엽니다!</h1>
	
	<div>
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="전송"/>
	<div id="chatdata">
		<input type="hidden" value="${loginUser.name }" id="userid">
		<input type="hidden" value="${cr.chatroom_no }" id="chatroom_no">
	</div>
	</div>
	<script type="text/javascript">
	//소켓 연결
	let sock = new SockJS("<c:url value="/echoroom"/>");
	
	//메세지전송
	sock.onmessage = onMessage;
	
	//연결 끊음
	sock.onclose = onClose;
	
	$(function(){
		$("#sendBtn").click(function(){
			sendMessage();
			console.log('send message...');
			$("#message").val('');
		})
	});
	
	
	function sendMessage(){
		var msgData = {
				user_id : $("#userid").val(),
				chatroom_no : $("#chatroom_no").val(),
				msg : $("#message").val()
		};
		var jsonData = JSON.stringify(msgData);//JSON.stringify란 자바스크립트의 값을 JSON 문자열로 변환한다. 
		sock.send(jsonData);
	}
	
	function onMessage(evt){
		var data = evt.data;
		var sessionid = null;
		var message = null;
		
		 var strArray = data.split('|'); 
		 //여기보다가 end
		 for(var i=0; i<strArray.length; i++){
				console.log('str['+i+']:' + strArray[i]);
			}
		 
		if(data.chatroom_no == $("#chatroom_no").val()){
		
			if(data.user_id == $("userid").val()){
				var printHTML = "<div>";
				printHTML += "<div>";
				printHTML += "<strong>["+sessionid +"] -> "+message+"</strong>";
				printHTML += "</div>";
				printHTML += "</div>";
			
				$("#chatdata").append(printHTML);
			}else{
				var printHTML = "<div>";
				printHTML += "<div>";
				printHTML += "<strong>["+sessionid+"] -> "+message+"</strong>";
				printHTML += "</div>";
				printHTML += "</div>";
				
				$("#chatdata").append(printHTML);
				
			}
		
		}else{
		
		}
		
	}
	
	function onClose(evt){
		$("#data").append("연결 끊김");
	}  
	</script>
</body>
</html>