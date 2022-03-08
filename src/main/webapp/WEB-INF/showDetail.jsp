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
<title>Tv Shows</title>
<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
rel="stylesheet"
integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
crossorigin="anonymous"
/>
</head>
<body>

  <div class="container">
    <div class="d-flex justify-content-between">
      <h1>${aShow.title}</h1>
      <a href="/shows">Back To Dashboard</a>
    </div>
    <p>Posted By - ${creator.userName}</p>
    
    <p>Network: <span class="ps-5 ms-4">${aShow.network}</span> </p>
    <p>Description:<span class="ps-5"> ${aShow.description}</span></p>

    <a href="/shows/${aShow.id}/edit" class="btn btn-warning">Edit</a>


    <hr>

    <table class="table">
      <thead>
        <tr>
          <th scope="col">Reviewer</th>
          <th scope="col">rate</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items='${ aShow.showRatings }' var='aRating'>
        
        <tr>          
          
          <td>${aRating.owner.userName}</td>
          <td>${aRating.score}</td>
         
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <form:form action="/shows/${aShow.id}/rate" method="post" modelAttribute="theRate">
      <form:errors path="owner" class="text-danger" />
      <form:input type="hidden" value=" ${curUser}" path="owner"/>
      <form:errors path="owner" class="text-danger" />
      <form:input type="hidden" value="${aShow.id}" path="aShow"/>
      <div class="d-flex d-inline ">
      <label class="me-5" for="">Leave a Rating</label>
      <form:errors path="score" class="text-danger" />
      <form:input path="score" type="number" class="form-control" style="width:80px"/>
      <button class="ms-4" type="submit">Rate!</button>
    </div>
   
    </form:form>
  </div>


</body>
</html>