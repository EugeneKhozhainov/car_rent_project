<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Car catalog</title>
</head>
<body>

<h3>Car catalog</h3>
<p style="color: red;">${error}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>ID</th>
        <th>Status</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${carList}" var="car" >
        <tr>
            <td>${car.id}</td>
            <td>${car.status}</td>
            <td>${car.brand}</td>
            <td>${car.model}</td>
            <td>${car.price}</td>
            <td>
                <a href="editCar?id=${car.id}">Edit</a>
            </td>
            <td>
                <a href="deleteCar?code=${car.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="createCar" >Create Car</a>

</body>
</html>
