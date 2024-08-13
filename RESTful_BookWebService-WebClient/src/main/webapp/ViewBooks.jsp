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
	<!-- 
	<form action="searchBook" method="get">
		<label>Book Name: </label>
		<input type="search" name="name" required />
		<button>Search</button>
	</form>
	<hr> 
	-->
	<%-- <c:if test="${result!=null}"> --%>
		<%-- <p style='background-color:yellow;padding:10px;'> ${result} </p> --%>
	<%-- </c:if> --%>
	
	<h3>All Books</h3>
	<c:forEach items="${books}" var="b">
		<div style="border:1px solid blue;padding:5px;margin:5px;height:250px;">
			<div style="float:left;">
				<p>Name: <b> <c:out value="${b.name}"/> </b> </p>
				<p>price: <b> <c:out value="${b.price}" /> </b> </p>
				<p>Author Name: <b> <c:out value="${b.aname}" /> </b> </p>
				<p>Publisher Name: <b> <c:out value="${b.pname}" /> </b> </p>
				<form action="deleteBook" method="post">
					<input type="hidden" name="name" value="${b.name}" required />
					<button>Delete Book</button>
				</form>
				<br/>
				<%-- <a href="/updateBook?name=${b.name}">Update Book</a> --%>
			</div>
			<img alt="" src="/getBookImage?name=${b.name}" height="200px" style="float:right;"> 
		</div> 
	</c:forEach>
	
</body>
</html>