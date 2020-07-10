<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
	<style>
 body {
            margin: 0;
            padding: 0;
        }

        .logo-area {
            
            display: inline-block;
            padding: 20px 30px;
        }


        .square {
            width: 15px;
            height: 15px;
            background-color: #F3F3F3;
            position: absolute;
            top: 58px;
            transform: rotate(45deg);
        }

        .login-area {
            
            position: relative;
            display: inline-block;
            float: right;
            padding : 20px 60px;

        }

        .login-area > div > a {
            text-decoration: none;
            color:rgba(72, 72, 72, 1);
            font-size: 18px;
            font-weight: 700;
            padding-left: 10px;
        }

        .login-area > div {
            display: inline-block;
            width: 25px;
            height : 26px;
            background-color: #FBD14B;
            border-radius: 50%;
            vertical-align: middle;
            margin-left: -60px;
        }

        .hide {
            display:none;
        }

        .login-sub {
            color:rgba(72, 72, 72, 1);
            text-align: center;
            left: -20px;
        }

        .login-square {
            left: 25%;
        }


</style>
</head>
<body>
	<div class="LoginArea" align="right">
      <c:if test="${empty sessionScope.loginUser}">
         <form action="login.do" method="post">
            <table id="loginTable" style="text-align:center">
               <tr>
                  <td>아이디</td>
                  <td><input type="text" name="id"></td>
                  <td rowspan="2">
                     <button type="submit" id="loginBtn">로그인</button>
                  </td>
               </tr>
               <tr>
                  <td>비밀번호</td>
                  <td><input type="password" name="pwd"></td>
               </tr>
               <tr>
                  <td colspan="3">
                     <a href="enrollview.do">회원가입</a>
                     <!-- 로그아웃까지 완료하고 나서 하자 -->
                     <a href="#">아이디/비밀번호 찾기</a>
                  </td>
                  
               </tr>
            </table>
         </form>
      </c:if>
      <c:if test="${!empty sessionScope.loginUser}">
         <!-- 여기 부분은 DB까지 길을 뚫고 만들자. -->
         <h3 align="right">
         <c:out value="${loginUser.name }님 환영합니다!!."/>
         <!-- 경로 변경시 수정할 변수 지정  -->
         <c:url var="logout" value="logout.do"/>
         <c:url var="myinfo" value="myinfo.do"/>
         
         <button onclick="location.href='${myinfo}'">정보수정</button>
         <button onclick="location.href='${logout}'">로그아웃</button>
 
         </h3>
      </c:if>
   </div>
   <br clear="both">
   <div>
		<label>* 채팅방 생성</label><br>
		<form id='tForm' method='get' action='chatRoominsertpage.do'>
			<p><button name='subject' type='submit'>채팅방 생성</button></p>
		</form>
	</div>
	
	<br clear="both">
	
	 <div>
		<label>* 채팅방 목록</label><br>
		<form name='TransTest' id='tForm' method='get' action='chatting.do'>
			<p><button name='subject' type='submit'>채팅방 리스트 보기</button></p>
		</form>
	</div>
</body>
</html>
