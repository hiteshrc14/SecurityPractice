<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.abc.user.UserManager"%>
<%@page import="java.util.UUID"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<%
		try {
			if (!session.getAttribute("csrf_token_session").toString().equals(request.getParameter("csrf_token"))) {
				throw new Exception("Invalid user");
			}
			String usercaptcha = request.getParameter("usercaptcha");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String sessionCaptcha = session.getAttribute("dns_security_code").toString();

			UserManager userManager = new UserManager();

			//remove captcha security code
			session.removeAttribute("dns_security_code");

			String resp = userManager.login(username, password, sessionCaptcha, usercaptcha);
			if (!resp.startsWith("Success")) {
	%>
	<script>
		alert("<%=resp%>");
		window.location = "Login.jsp";
	</script>
	<%
		} else {
				String userId = resp.replaceAll("Success:", "");
				String csrf = UUID.randomUUID().toString();
				session.invalidate();
				session = request.getSession(true);
				session.setAttribute("userId", userId);
				session.setAttribute("csrf_token_session", csrf);
				session.setAttribute("username", username);
				session.setAttribute("userLoggedIn", "true");
				//redirect to User's home page
				response.sendRedirect("UserHome.jsp");
			}
		} catch (Exception e) {
			response.sendRedirect("Login.jsp");
		}
	%>
</body>
</html>