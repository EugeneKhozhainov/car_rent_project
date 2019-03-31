<%--
  Created by IntelliJ IDEA.
  User: eugenekhozhainov
  Date: 31/03/2019
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/editCar">
    <input type="hidden" name="id" value="${car.id}" />
    <input type="hidden" name="status" value="${car.status}" />
    <table border="0">
        <tr>
            <td>Brand</td>
            <td><input type="text" name="brand" value="${car.brand}" /></td>
        </tr>
        <tr>
            <td>Model</td>
            <td><input type="text" name="model" value="${car.model}" /></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="price" value="${car.price}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
                <a href="/carList">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
