<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>회원인증</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
<div class="login-page">
<div class="form">
  <form:form class="login-form" modelAttribute="user" method="post" action="login.do">

     <p align="left"><span class="fieldError"><form:errors path="id" /></span></p> <!-- valid에서 정의한 메세지?? -->
     <span class="fieldError">${errMsg} </span></p> <!-- 아이디가 없는 것이라면 출력되는 것  -->
     <form:input path='id' type="text" placeholder="username"/>  
     
     <p align="left"><span class="fieldError"><form:errors path="pass" /></span></p>
     <form:input path='pass' type="password" placeholder="password"/>
     
     <button type="submit">login</button>
     <p class="message"><ul id="ullog">
	  <li id="lilogb"><a href="/doc/join.do">회원가입</a></li>|
	   <li id="lilog"><a href="/doc/findId_form.do">아이디/</a>  
	 <!--  <li id="lilog"><a href="/doc/findId.do">아이디/</a> -->
               <a href="/doc/find_pw_form.do" class="pwd">비밀번호 찾기</a></li>
            <!--     <a href="/doc/findPass.do" class="pwd">비밀번호 찾기</a></li> -->
     </ul></p>
     
  </form:form>
</div></div>
</body>
</html>
