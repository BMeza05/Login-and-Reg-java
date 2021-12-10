<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Tasks</title>
</head>
<body>
	<h1>Welcome, <c:out value="${user.name}" /></h1>
	<div>
		<c:forEach items="${tasks}" var="task">
			<p> <a href="tasks/${task.id}/edit" ><c:out value="${task.content}" /></a> <a href="tasks/${task.id}/details">Detail</a> </p>
			<p> <c:out value="${task.assignee}"/> </p>
			<p> <c:out value="${task.priority}"/> </p>
			<p><c:out value="${task.user.name}" /> </p>
		</c:forEach>
	</div>
	
	<a href="/logout">Logout</a>
	
	<a href="/tasks/new">New task</a>

</body>
</html>