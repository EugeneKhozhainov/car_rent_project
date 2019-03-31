package servlet;

import database.ConnectionUtils;
import entity.CarEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/editCar")
public class UpdateCarCatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionUtils.getConnection();
        try{
            String sql = "select e.* from car e where e.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(req.getParameter("id")));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                CarEntity entity = new CarEntity();
                entity.setId(rs.getLong("id"));
                entity.setBrand(rs.getString("brand"));
                entity.setModel(rs.getString("model"));
                entity.setPrice(rs.getDouble("price"));
                entity.setStatus(rs.getString("status"));
                req.setAttribute("car", entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/WEB-INF/update_car_catalog.jsp").forward(req, resp);
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
            ps.setLong(5, Long.valueOf(req.getParameter("id")));
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/carList");
    }
}
