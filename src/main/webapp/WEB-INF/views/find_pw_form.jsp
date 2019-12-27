<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>비밀번호 찾기</title>
</head>
<body>
<form action="/doc/find_pw.do" method="post">
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
				<h3>비밀번호 찾기</h3>
			</div>
			<div>
				<p>
					<label>ID</label>
					<input class="w3-input" type="text" id="id" name="id" required>
					<span class="fieldError">${errMsg}</span>
				</p>
				 <p>
					<label>Name</label>
					<input class="w3-input" type="text" id="name" name="name" required>
				</p> 
				<p class="w3-center">
					<button type="submit" id=findBtn class="w3-button w3-block w3-blue w3-ripple w3-margin-top w3-round">find</button>
					<button type="button" onclick="history.go(-1);" class="w3-button w3-block w3-red w3-ripple w3-margin-top w3-round">Cancel</button>
				</p>
			</div>
		</div>
	</div>
	</form>
</body>
</html>