<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	
<%@ include file="./jsp_header.jsp"%> <!-- jstl을 사용할 수 있게 해준 것  -->
<html>
<head>
<title>탈퇴 화면</title>

<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
	border: 3px solid skyblue;
}
.delete0 {
	font-size: 50px;
	text-shadow: 0 0 10px #666;
	color: #ff0000;
	margin: 0 auto;
	text-align: center;
	text-transform: capitalize;
	font-family: fantasy;
	font-style: oblique;
}
.delete1 {
	font-size: 50px;
	text-shadow: 0 0 10px #666;
	color: #ff00;
	margin: 0 auto;
	text-align: center;
	text-transform: capitalize;
	font-family: fantasy;
	font-style: oblique;
}

input[type="button"] {
	height: 30px;
	background: gray;
	border-radius: 5px;
	/*  width: 140px; */
	font-family: "맑은 고딕";
	margin-top: 10px;
	margin-right: 20px;
}


input[type="submit"] {
	height: 30px;
	background: red;
	border-radius: 5px;
	/*  width: 140px; */
	font-family: "맑은 고딕";
	margin-top: 10px;
	margin-right: 20px;
}

td {
	border: 1px solid skyblue
}

#title {
	background-color: skyblue
}
#page-wrapper {
	background:#CEF6F5;
	margin: 40px 0;
	padding: 10px 20px;
	border-radius: 5px;
	box-shadow: 0 2px 6px rgba(100, 100, 100, 0.3);
}
body{
	background-image:url('img/images6.jpg');
	margin: 40px 0;
	padding: 10px 20px;
	border-radius: 5px;
	box-shadow: 0 2px 6px rgba(100, 100, 100, 0.3);
}
</style>

<script type="text/javascript">
	// 비밀번호 미입력시 경고창
	function checkValue() {
		if (!document.deleteform.password.value) {
			alert("비밀번호를 입력하지 않았습니다.");
			return false;
		}

	}
</script>

</head>
<body>
<div id="page-wrapper">
	<br>
	<br>
	<h2 class=delete0>탈퇴</h2>
	<h2 class=delete1>시 모든 정보가 삭제됩니다.</h2>
	<br>
	<br>
	<br>
	
	
<form:form modelAttribute="user" Name='joinForm' Method='post' Action='/doc/login.do' onsubmit='return checkValue()'>
		<table>
			<tr>
			<p align="left"><span class="fieldError"><form:errors path="pass" /></span></p>
				<td bgcolor="gray">비밀번호</td>
				<td><input type="password" name="password" maxlength="50"></td>
			</tr>
			
		</table>

		<br> <input type="button" value="취소"
			onclick="javascript:window.location='joinSuccess.jsp'"> 
			<input type="submit" value="탈퇴" />  
     
     
     
	</form:form>
	</div>
</body>
</html>