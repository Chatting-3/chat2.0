<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatList</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h1>채팅방 리스트</h1>
		<table align="center" border="1" cellspacing="0" width="700" id="td">
		<tr>
			<th>번호</th>
			<th width="300">방이름</th>
		</tr>
		<c:forEach var="list" items="${list }">
			<tr>
				<td align="center">${list.chatroom_no}</td>
				<td align="left">
					<c:url var="chatroom" value="chatroomjoin.do">
						<c:param name="roomnumber" value="${list.chatroom_no}"/>
					</c:url>
				<a href="${chatroom }">${list.chatroomname}</a>
				</td>
			</tr>
			</c:forEach>
			
	</table>


</body>
</html>