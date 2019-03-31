<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title></title>
  </head>
  <body>

  <h1>New Order</h1>
  <p>Car:<select>
  <c:forEach var="item" items="${carList}">
          <option>${item.getDescription()}</option>
      </c:forEach>
  </select></p>

  <p>Date from:<input type="date" name="dateFrom"></p>
  <p>Date to:<input type="date" name="dateTo"></p>
  <p>Passport: <input type="text" name="passport"></p>

  </body>
</html>