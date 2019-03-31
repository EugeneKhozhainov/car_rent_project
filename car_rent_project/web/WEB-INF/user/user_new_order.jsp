<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title></title>
  </head>
  <body>

  <h1>New Order</h1>

  <form method="POST" action="${pageContext.request.contextPath}/userNewOrder">
  <p>Car:<select  name="car" >
  <c:forEach var="car" items="${carList}">
          <option value=${car.id}> ${car.getDescription()}</option>
      </c:forEach>
  </select></p>

  <p>Date from:<input type="date" name="dateFrom"></p>
  <p>Date to:<input type="date" name="dateTo"></p>
  <p>Passport: <input type="text" name="passport"></p>
  <input type="submit" value="Submit" />

  </form>
  </body>

</html>