<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="com.abc.user.Utils"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.io.FileInputStream"%>
<%
	if (session.getAttribute("userLoggedIn") != null && session.getAttribute("userId") != null) {
		if (session.getAttribute("userLoggedIn").toString().equals("true")) {
			try{
			String username = session.getAttribute("username").toString();
			String fileId = request.getParameter("fileId");
			
			
			//user have permissions so show file to user
			String fileName = request.getParameter("filename");
			ResourceBundle bundle = ResourceBundle.getBundle("com.abc.user.db");
			String appPath = bundle.getString("DIRPATH");
			String uploadPath = appPath + File.separator + "uploadFiles/";
			
			//clear all response headers
			response.reset();
			response.resetBuffer();
			
			// view file code
			FileInputStream fileInputStream = new FileInputStream(uploadPath + fileId);
			
			//add attachment header in response
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			int i;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
			fileInputStream.close();
			}catch (Exception e)
			{
				%>
				<script>alert("File not found");
				window.location="UserHome.jsp";</script>
				<%
			}
		} else {
			response.sendRedirect("Logout.jsp");
		}
	} else {
		response.sendRedirect("Logout.jsp");
	}
%>