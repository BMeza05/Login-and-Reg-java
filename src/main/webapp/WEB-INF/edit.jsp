<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>    

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit task</title>
</head>
<body>
	<h1>Edit your task!</h1>
    
    <p><form:errors path="task"/></p>
    
    <form:form method="POST" action="/tasks/${task.id}/edit" modelAttribute="task">
    	 <input type="hidden" name="_method" value="put">
        <p>
            <form:label path="content">Task:</form:label>
            <form:errors path="content" />
            <form:input type="text" path="content"/>
        </p>
        <form:hidden path="user" value="${task.user.id}" />
        
	   	<p>
	    	<form:label path="assignee">Assignee</form:label>
	    	<form:errors path="assignee" />
	    	<form:select path="assignee" >
	    		<form:option value="assignee">Jonathan Doe</form:option>
	    		<form:option value="assignee">Rebecca</form:option>
	    		<form:option value="assignee">Jerry</form:option>
	    	</form:select>
	    </p>
	    
	     <p>
	    	<form:label path="priority">Priority</form:label>
	    	<form:errors path="priority" />
	    	<form:select path="priority" >
	    		<form:option value="priority">High</form:option>
	    		<form:option value="priory">Low</form:option>
	    	</form:select>
	    </p>
    	
        <input type="submit" value="update task"/>
    </form:form>
    <form action="/tasks/${task.id}/edit" method="post">
    	<input type="hidden" name="_method" value="delete">
    	<input type="submit" value="Delete">
	</form>
</body>
</html>