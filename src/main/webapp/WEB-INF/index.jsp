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
<title>TV Shows</title>
<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
rel="stylesheet"
integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
crossorigin="anonymous"
/>
</head>
<body>
<div class="container">
  <div class="d-flex justify-content-evenly">
    <h1>Welcome, ${currentUser.userName} </h1>
    <a href="/logout">Logout</a>
  </div>

<h5>TV Shows</h5>

<table class="table table-striped ">
  <thead>
    <tr classs="table-light">
      <th scope="col">Show</th>
      <th scope="col">Network</th>
      <th scope="col">Average Rating</th>
    </tr>
  </thead>
  <tbody class="table-primary ">
    <c:forEach items='${ allShows }' var='show'>
      <tr>
        <td> <a href="/shows/${show.id}">${show.title}</a></td>
        <td>${show.network}</td>
        
        <c:choose>
          <c:when test='${show.showRatings.size()==0}'>
           <td>No Rating</td> 
          </c:when>
          <c:otherwise>
            <td>${show.avgRating()}</td>
          </c:otherwise>
        </c:choose>


        
      </tr>
    </c:forEach>
  </tbody>
</table>

<div>
<a href="/shows/new" class="btn btn-primary">Add Show</a>
</div>



</div>
</body>
</html>