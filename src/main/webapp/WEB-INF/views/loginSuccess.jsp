<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>로그인화면</title>

<script>
$(doucument).ready(function() {
	$("#logoutBtn").unbind("click").click(function(e){
		e.prevnetDefault();
		logout();
	});
	
});
function logout() {
if(window.confirm("로그아웃을 하시겠습니까?"));
{
	window.location.href="/doc/logout.do";
	}

}

</script>
</head>
<% System.out.println("LoginSuccess.jsp ==> 로그인 성공 화면:"); %>
<body>
	<div align="center" class="body">
		<h2>로그인화면</h2>
		<br>
		환영합니다,${loginUser.name}씨！
		<br><br><br>
	<a href="/doc/edit.do?userId=${loginUser.id}">[회원정보 수정]</a>   <!-- get 방식을 이용한 것  -->
						 <!-- 해당 유저id의 값을 가지고 해당 아이디의 수정 페이지 부분으로 이동을 한다 -->
	<br><br>
	
	<a href="/doc/logout.do">[logout]</a> 
	</div>	
	
</body>
</html>