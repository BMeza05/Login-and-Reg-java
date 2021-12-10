<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<%@ page isErrorPage="true" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New task</title>
</head>
<body>
	<h1>Create your task!</h1>
    
    <p><form:errors path="task"/></p>
    
    <form:form method="POST" action="/tasks/new" modelAttribute="task">
        <p>
            <form:label path="content">Task:</form:label>
            <form:errors path="content" />
            <form:input type="text" path="content"/>
           	<form:hidden path="user" value="${user.id}"/>
        </p>
        
        <p>
        	<form:label path="assignee">Assignee</form:label>
        	<form:errors path="assignee" />
        	<form:select path="assignee" >
        		<option value="" disabled selected>
        		
        		<form:option value="Jonathan Doe">Jonathan Doe</form:option>
        		<form:option value="Rebecca">Rebecca</form:option>
        		<form:option value="Jerry">Jerry</form:option>
        	</form:select>
        </p>
        
         <p>
        	<form:label path="priority">Priority</form:label>
        	<form:errors path="priority" />
        	<form:select path="priority" >
        		<option value="" disabled selected>
        		
        		<form:option value="High">High</form:option>
        		<form:option value="Low">Low</form:option>
        	</form:select>
        </p>
        <input type="submit" value="create task"/>
        
    </form:form>
	
	
</body>
</html>