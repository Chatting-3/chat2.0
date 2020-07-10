<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>채팅방 생성</h1>
<form action="chatroominsert.do" method="post">
	<table align="center">
		<tr>
			<td>방 이름</td>
			<td><input type="text" name="chatroomname"></td>
		</tr>
		<tr>
			<td>방 비밀번호</td>
			<td><input type="text" name="chatpassword"></td>
		</tr>
		<tr>
			<td></td>
			<td align="right"><button type="submit">방생성</button></td>
		</tr>
	</table>

</form>

</body>
</html>