<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logout</title>
</head>
<body>
	<%
		try {
			//clear session
			session.invalidate();
			Cookie[] cookies = request.getCookies();

			//remove jsessionid cookie manually
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("JSESSIONID")) {
					cookies[i].setMaxAge(-1);
				} else {
					cookies[i].setMaxAge(0);
				}
				cookies[i].setValue(null);
				cookies[i].setPath("/");
				response.addCookie(cookies[i]);
			}
		} catch (Exception e) {
		}
		response.sendRedirect("Login.jsp");
	%>

</body>
</html>