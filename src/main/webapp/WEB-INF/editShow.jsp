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
    <h1>Edit  ${aShow.title}</h1>
   
    <form:form action="/shows/update" method="post" class="w-50" modelAttribute="aShow">
      <input type="hidden" name="_method" value="put"> 
      <form:errors path="id" class="text-danger"/>
      <form:input type="hidden" value="${aShow.id}" path="id"/>
      <form:errors path="person" class="text-danger"/>
      <form:input type="hidden" value="${show.person.id}" path="person"/>
      <form:errors path="title" class="text-danger"/>
      <div class="form-floating mb-3 ">
        <form:input path="title" type="text" class="form-control" placeholder="1" />
        <label for="floatingInput">Title</label>
      </div>
      <form:errors path="network" class="text-danger"/>
      <div class="form-floating mb-3">
        <form:input  path="network" type="text"  class="form-control" placeholder="0" />
        <label for="floatingPassword">Network</label>
      </div>
      <form:errors path="description" class="text-danger"/>
      <div class="form-floating mb-3">
        <form:input path="description" type="text" class="form-control" placeholder="0" />
        <label for="floatingPassword">Description</label>
      </div>
      <div >
        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="/shows" class="btn btn-warning">Cancel</a>
      </div>
    </form:form>


 

  </div>
  <div class="container mt-5">
  <a href="/shows/${aShow.id}/delete" class="btn btn-danger">Delete Show</a>
</div>


</body>
</html>