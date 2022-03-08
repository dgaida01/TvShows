<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Login</title>
<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
rel="stylesheet"
integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
crossorigin="anonymous"
/>
</head>
<body>
  <div class="contaner d-flex justify-content-center">
   
  <h1 style="color:cadetblue">TV Shows Database</h1>
  </div>
</div>
<div class="container row ">
  <!-- Registration Section -->
  <div class="col-6">
  <h1>Registration</h1>
  <form:form action="/user/register" method="post" modelAttribute="newUser">
    <p>
        <form:label path="userName">User Name:</form:label>
        <form:errors path="userName"  class="text-danger"/>
        <form:input path="userName" class="form-control"/>
    </p>
    <p>
        <form:label path="email">Email:</form:label>
        <form:errors path="email" class="text-danger"/>
        <form:input path="email" type="email" class="form-control"/>
    </p>
    <p>
        <form:label path="password">Password:</form:label>
        <form:errors path="password" class="text-danger"/>
        <form:input path="password" type="password" class="form-control"/>
    </p>
    <p>
        <form:label path="confirm">Confirm Password:</form:label>
        <form:errors path="confirm" class="text-danger"/>     
        <form:input type="password" path="confirm" class="form-control"/>
    </p>    
    <input type="submit" value="Submit"/>
</form:form>
</div> 
<!-- Login Section -->
<div class="col-6">
  <h1>Login</h1>
  <form:form action="/user/login" method="post" modelAttribute="loginUser">
    <p>
        <form:label path="email">Email:</form:label>
        <form:errors path="email" class="text-danger"/>
        <form:input path="email" type="email" class="form-control"/>
    </p>
    <p>
        <form:label path="password">Password:</form:label>
        <form:errors path="password" class="text-danger"/>
        <form:input path="password" type="password" class="form-control"/>
    </p>   
    <input type="submit" value="Submit"/>
</form:form>
</div> 
</div>

</body>
</html>