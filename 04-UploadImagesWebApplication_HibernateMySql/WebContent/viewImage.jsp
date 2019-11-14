<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.light.hibernate.entity.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Image </title>
</head>
<body>
<%Files file; 
String path;
file= (Files)request.getAttribute("file");
path=(String)request.getAttribute("path");

%>

File ID: <%=file.getId() %> | File name: <%=file.getFileName() %>
<%
	if(file.getCaption()!=null|| file.getLabel()!=null){
		out.print("Label: " +file.getLabel()+ "| Caption: " + file.getCaption());
	}

%>
<a href="${pageContext.request.contextPath }">Home</a>
<a href="${pageContext.request.contextPath }/ImageUpload?param=listingImages">List Available </a>

<img src="<%= path + file.getFileName()%>">
</body>
</html>