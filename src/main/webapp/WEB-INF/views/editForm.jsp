<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="./jsp_header.jsp"%> <!-- jstl을 사용할 수 있게 해준 것  -->
<HTML>
<HEAD>
<TITLE> 회원 정보 수정 </TITLE> 

<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
   
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function DaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {

                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                if(data.userSelectedType === 'R'){

                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
  
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zip').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('addr1').value = fullAddr;    //우리가 DB에 설정한 데이터명을 ('')안에 넣는다.

                document.getElementById('addr2').value = "";
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('addr2').focus();
            }
        }).open();
    }
</script>
</HEAD>

<BODY>
<br>
<center><font size='3'><b> 회원정보 수정 </b></font>  
<hr size='1' noshade width='600' align="center">

<p align="left"><span class="fieldError">${errMsg}  <!-- <form:errors path="id" />  --> </span></p> 
<br>

<form:form  modelAttribute="user" Name='joinForm' Method='post' Action='/doc/edit.do'> <!-- 아래 submit클릭시 action ~ -->

<TABLE  border='2' width='600'  cellSpacing=0 cellPadding=5 align=center>
<TR>
	<TD bgcolor='cccccc' width='100' align='center'>
		<font size='2'>아 이 디</font>
	</TD>
	<TD bgcolor='cccccc'>
		<form:input path="id" maxLength='20' size='10' cssClass="userId" readonly='true' />
		<span class="fieldError"><form:errors path="id" /></span>
	</TD>
</TR>
<TR>
	<TD bgcolor='cccccc' align='center'>
		<font size='2'>비 밀 번 호</font>
	</TD>
	<TD bgcolor='cccccc'>
		<form:password path="pass" maxlength="20" cssClass="password"  size='10' showPassword="true" />
		<span class="fieldError"><form:errors path="pass" /></span>
	</TD>
</TR>
<TR>
	<TD bgcolor='cccccc' align='center'>
		<font size='2'>비밀번호확인</font>
	</TD>
	<TD bgcolor='cccccc'>
		<input type="password" name="pass2" maxlength="20" cssClass="password"  size='10' value="" showPassword="true" />
	</TD>
</TR>
<TR>
	<TD bgcolor='cccccc' align='center'>
		<font size='2'>이 름</font>
	</TD>
	<TD bgcolor='cccccc'>
		<form:input path="name" maxlength="20" cssClass="name" size='10' readonly='true'/> <!-- 이 readonly =true 는 고칠수 없게 설정한것  --> 
		<span class="fieldError"><form:errors path="name" /></span>
	</TD>
</TR>
<TR>
	<TD bgcolor='cccccc' align='center'>
		<font size='2'>우 편 번 호</font><span class="fieldError">
	</TD>
	<TD bgcolor='cccccc'>
		<form:input path='zip' maxlength='7' size='7' />
		<input  type='button' OnClick='DaumPostcode()' value='우편번호검색'>
		<span class="fieldError"><form:errors path="zip" /></span>
	</TD>
</TR>
<TR>
	<TD bgcolor='cccccc' align='center'>
		<font size='2'>주 소</font>
	</TD>
	<TD bgcolor='cccccc'>
		<form:input  maxlength='50' size='50' path='addr1' /><span class="fieldError" >
		<form:errors path="addr1" /></span><BR>
		<form:input  maxlength='50' size='50' path='addr2' /><span class="fieldError" >
		<form:errors path="addr2" /></span>
	</TD>
</TR>
<TR>
	<TD bgcolor='cccccc' align='center'>
		<font size='2'>전 화 번 호</font>
	</TD>
	<TD bgcolor='cccccc'>
		<form:input type='text' maxlength='20' size='20' path='phone' />
		<span class="fieldError" ><form:errors path="phone" /></span>
	</TD>
</TR>
<TR>
	<TD bgcolor='cccccc' align='center'>
		<font size='2'>E - M a i l</font>
	</TD>
	<TD bgcolor='cccccc'>
		<form:input type='text' maxlength='50' size='50' path='email' />
		<span class="fieldError" ><form:errors path="email" /></span>
	</TD>
</TR>
</TABLE>

<br>
<hr size='1' noshade width='600' align="center">

<TABLE>
<TR><TD colspan='2' align='center'><input type='submit' value='수정'></TD></TR>
<TR><TD colspan='2' align='center'><a href = "delete.do"><input type="button" value="탈퇴"></a></TD></TR>

</TABLE>

</form:form>

</BODY>
</HTML>