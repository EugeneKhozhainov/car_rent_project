<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Product</title>
</head>

<body>

<h3>Delete Product</h3>

<tr>
    <td>Id</td>
    <td><input type="text" name="id" value="${car.id}" /></td>
</tr>
<input type="submit" value="Submit" />

</body>
</html>