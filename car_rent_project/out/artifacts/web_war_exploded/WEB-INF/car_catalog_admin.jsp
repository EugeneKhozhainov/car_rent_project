<%--
  Created by IntelliJ IDEA.
  User: eugenekhozhainov
  Date: 31/03/2019
  Time: 07:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title123321123</title>
</head>
<body>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
    String driverName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:postgresql://localhost:5432/";
    String dbName = "postgres";
    String userId = "postgres";
    String password = "postgres";

    try {
        Class.forName(driverName);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
%>
<h2><strong>Retrieve data from database in jsp</strong></h2>

    <tr>


        <td><b>id</b></td>
        <td><b>brand</b></td>
        <td><b>model</b></td>
        <td><b>price</b></td>
        <td><b>status</b></td>
    </tr>
    <%
        try{
            connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
            statement=connection.createStatement();
            String sql ="SELECT * FROM record";

            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
    %>
    <tr bgcolor="#DEB887">

        <td><%=resultSet.getString("id") %></td>
        <td><%=resultSet.getString("brand") %></td>
        <td><%=resultSet.getString("model") %></td>
        <td><%=resultSet.getString("price") %></td>
        <td><%=resultSet.getString("status") %></td>

    </tr>

    <%
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>

</body>
</html>
