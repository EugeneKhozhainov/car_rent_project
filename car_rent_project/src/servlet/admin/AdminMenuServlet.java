package servlet.admin;

import database.ConnectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/adminMenu")
public class AdminMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionUtils.getConnection();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        StringBuilder errors = new StringBuilder();

        if (username == null || username.equals("")) {
            errors.append("Username is null<br>");
        }

        if (password == null || password.equals("")) {
            errors.append("Password is null<br>");
        }

        try {
            PreparedStatement statement = connection.prepareStatement("select e.* from users e where e.role = ? and e.username = ?");
            statement.setString(1, "Admin");
            statement.setString(2, username);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (!rs.getString("password").equals(password)) {
                    errors.append("Invalid password<br>");
                }
            } else {
                errors.append("Invalid user<br>");
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            errors.append(e);
            e.printStackTrace();
        }

        RequestDispatcher dispatcher;

        if (errors.length() > 0) {
            request.setAttribute("error", errors.toString());
            dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin_login_error.jsp");
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin_menu.jsp");
        }

        dispatcher.forward(request, response);
    }

}
