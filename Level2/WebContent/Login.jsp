<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.UUID"%>
<%@page import="com.abc.user.Captcha"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<%
		String csrf_token = UUID.randomUUID().toString();
		session.setAttribute("csrf_token_session", csrf_token);
		Captcha c = new Captcha(request, response);
	%>

	<h2>Login</h2>
	<p>The Coolest have to share using Cloud Sharing</p>
	<form action="LoginProcess.jsp" method="post" autocomplete="off">
		<input type="hidden" name="csrf_token" value="<%=csrf_token%>">

		<div>
			<strong>Username:</strong>&nbsp;<input type="text"
				placeholder="Username" name="username">
		</div>
		<div>
			<strong>Password:</strong>&nbsp;&nbsp;<input type="password"
				placeholder="********" name="password">
		</div>
		<br> <br>
		<div>
			<blockquote>Captcha-Prove Human*</blockquote>
		</div>
		<div>
			<img src="captcha/<%=c.captcha%>.jpeg">&nbsp;&nbsp;<input type=text
				name=usercaptcha placeholder="Captcha">
		</div>
		<div>
			<input type="Submit" value="Login">&nbsp;&nbsp;<input
				type="Reset" value="Reset">
		</div>
	</form>
</body>
</html>