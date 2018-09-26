<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.abc.user.File"%>
<%@page import="com.abc.user.FileManager"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.abc.user.SharedFile"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<%
		if (session.getAttribute("userLoggedIn") != null && session.getAttribute("userId") != null
				&& session.getAttribute("userLoggedIn").toString().equals("true")) {
	%>
	<h2>Welcome</h2>
	<p>The Coolest have to share using Cloud Sharing</p>
	<h4>You can :</h4>
	<p>
		Add New Files to upload here and<br>Delete files here and <br>Change
		Access of who can read and write to a particular
	</p>
	<table>
		<thead>
			<tr>
				<th>Sr No</th>
				<th>&nbsp;</th>
				<th>File Names</th>
				<th>&nbsp;</th>
				<th>Actions</th>
				<th>&nbsp;</th>
				<th>File Owner</th>
				<th>&nbsp;</th>
				<th>Users with Read Access</th>
				<th>&nbsp;</th>
				<th>Users with Write Access</th>
				<th>&nbsp;</th>
				<th>Users with Read/Write Access</th>
			</tr>
		</thead>
		<tbody>
			<%
				// get logged in user details from session
					String userId = session.getAttribute("userId").toString();
					String userName = session.getAttribute("username").toString();

					//get all files uploaded by this user
					FileManager fileManager = new FileManager();
					List<File> files = fileManager.getFileList(userName);

					//iterate all of it
					Iterator<File> iterator = files.iterator();
					int cnt = 1;

					while (iterator.hasNext()) {
						File file = iterator.next();
						//display information
						out.println("<tr>");
						out.println("<td>" + cnt++ + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(file.getFileName()) + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td><a href='ViewFile.jsp?fileId=" + StringEscapeUtils.escapeHtml(file.getFileId())
								+ "&filename=" + StringEscapeUtils.escapeHtml(file.getFileName()) + "'>View</a></td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(file.getFileOwner()) + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(file.getReadUsers()) + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(file.getWriteUsers()) + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(file.getReadWriteUses()) + "</td>");
						out.println("</tr>");
					}

					//get all files shared with this user
					List<SharedFile> sharedFiles = fileManager.getFileSharedWithMe(userName);
					Iterator<SharedFile> iterator2 = sharedFiles.iterator();
					while (iterator2.hasNext()) {
						SharedFile sharedFile = iterator2.next();
						//display information
						out.println("<tr>");
						out.println("<td>" + cnt++ + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(sharedFile.getFileName()) + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td><a href='ViewFile.jsp?fileId="
								+ StringEscapeUtils.escapeHtml(sharedFile.getFileId()) + "&filename="
								+ StringEscapeUtils.escapeHtml(sharedFile.getFileName()) + "'>View</a></td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(sharedFile.getOwnerName()) + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(sharedFile.getRead()) + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(sharedFile.getWrite()) + "</td>");
						out.println("<td>&nbsp;</td>");
						out.println("<td>" + StringEscapeUtils.escapeHtml(sharedFile.getReadWrite()) + "</td>");
						out.println("</tr>");
					}
					fileManager.close();
			%>
		</tbody>
	</table>

	<p>
		<a href="Logout.jsp">Logout</a>
	</p>
	<%
		} else {
			response.sendRedirect("Login.jsp");
		}
	%>
</body>
</html>