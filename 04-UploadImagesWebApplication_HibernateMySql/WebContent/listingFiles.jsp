<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.light.hibernate.entity.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listing Images</title>
</head>
<body>

<%! String form;int filedId; %>

<table border="1">
<tr>
<th>Preview</th>
<th>Available Information</th>
<th>Update Information</th>
<th>Available Action</th>
</tr>
<%
String path =(String) request.getAttribute("path");
List<Files> files =(List<Files>) request.getAttribute("listOfFiles");

for(Files file:files)
{
	out.print("<tr><td><img src="+path+file.getFileName()+" height='200' width='360'></td>");
	out.print("<td><ul>"
			+"<li> File ID: "+file.getId()+" </li>"
			+"<li> File Name: "+file.getFileName()+"</li>" 
			+"<li>File Label: "+file.getLabel()+"</li>"
			+"<li> File Caption: "+file.getCaption()+"</li>"
			+ "</ul></td>");
	
	filedId=file.getId();
	form= "<form action ='ImageUpload' method='post'>"
			 +"  Label: <input type='text' name='label'/></br>"
			 +"Caption: <input type='text' name='caption'/><br>"
			 +"<input type='hidden' name='fileId' value="+filedId+">"
			 +"<input type='hidden' name='param' value='updateField'>"
			 +"<input type='submit'value='Update'/>"
				+"</form>";
	out.print("<td>"+form+"</td>");
	out.print("<td><ul><li>	<a href='"+request.getContextPath()+"/ImageUpload?param=actionImage&fileId="+file.getId()+"'>View Image</a></li>");
	
	out.print("<li>	<a href ='"+request.getContextPath()+"/ImageUpload?param=deleteImage&fileId="
	+file.getId()+"' onclick=\"if(!confirm('Are you sure you want to delete this user?')) return false\">Delete Image</a></li></ul></td></tr>");

}
%>

</table>
</body>
</html>