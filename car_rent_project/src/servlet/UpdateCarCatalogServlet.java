package servlet;

import database.ConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/editCar")
public class UpdateCarCatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/update_car_catalog.jsp").forward(req, resp);
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        Double price = Double.valueOf(req.getParameter("price"));
        String status = req.getParameter("status");
        Integer id = Integer.valueOf(req.getParameter("id"));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
        Connection connection = ConnectionUtils.getConnection();
        String sql = "Update car set brand =?, model=?, price=?, status=? where id=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, req.getParameter("brand"));
            ps.setString(2, req.getParameter("model"));
            ps.setDouble(3, Double.valueOf(req.getParameter("price")));
            ps.setString(4, req.getParameter("status"));
            ps.setInt(5, Integer.valueOf(req.getParameter("id")));
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/carList");
    }
}
