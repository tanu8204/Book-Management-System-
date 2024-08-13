<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1> ${appName} </h1>
	<hr>
	<a href="/">Home</a> 
	<a style="margin-left:20px;" href="/viewBooks">View Books</a>
	<hr>
	<h3>Add Book</h3>
	<c:if test="${result!=null}">
		<p style='background-color:yellow;padding:10px;'> ${result} </p>
	</c:if>
	<form action="addBook" method="post" enctype="multipart/form-data">
		Book Name: <input type="text" name="name" required/><br/><br/>
		Book Price: <input type="number" name="price" required/><br/><br/>
		Book Author Name: <input type="text" name="aname" required/><br/><br/>
		Book Publisher Name: <input type="text" name="pname" required/><br/><br/>
		Book Photo: <input type="file" accept="image/*" name="image" /><br/><br/>
		<input type="submit" value="Add Book"/>
	</form>
</body>
</html>