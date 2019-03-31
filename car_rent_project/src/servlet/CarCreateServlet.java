package servlet;

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
import java.sql.SQLException;

@WebServlet("/createCar")
public class CarCreateServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/add_car_entry.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = "Insert into car(brand, model, price, status) values (?,?,?,?)";
        Connection con = ConnectionUtils.getConnection();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, req.getParameter("brand"));
            pr.setString(2, req.getParameter("model"));
            pr.setDouble(3, Double.valueOf(req.getParameter("price")));
            pr.setString(4, "working");
            pr.executeUpdate();
            pr.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/carList");
    }
}
