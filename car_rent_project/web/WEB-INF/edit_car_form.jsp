<%--
  Created by IntelliJ IDEA.
  User: eugenekhozhainov
  Date: 31/03/2019
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create car entry</title>
</head>
<body>

<h3>Create car entry</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/create">
    <table border="0">
        <tr>
            <td>Brand</td>
            <td><input type="text" name="brand" value="${car.code}" /></td>
        </tr>
        <tr>
            <td>Model</td>
            <td><input type="text" name="model" value="${car.name}" /></td>
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
