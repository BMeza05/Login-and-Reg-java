<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task details</title>
</head>
<body>
	
	<p>
		<h1>Task: <c:out value="${task.content}"/> </h1>
		
		<h2>Creator: <c:out value="${user.name}" /></h2>
		
		<h2>Priority: <c:out value="${task.priority}" /></h2>
		
		<h2>Assignee: <c:out value="${task.assignee}"/></h2>
		
		<p> <a href="/tasks/${task.id}/edit"> Edit</a></p>
		
		<form action="/tasks/${idea.id}/edit" method="post">
    		<input type="hidden" name="_method" value="delete">
    		<input type="submit" value="Delete">
	     </form>
		
		
	</p>
	
</body>
</html>