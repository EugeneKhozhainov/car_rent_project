package servlet;

import database.ConnectionUtils;
import entity.CarEntity;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/carList"})
public class CarCatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionUtils.getConnection();

        String error = null;
        List<CarEntity> carList = new ArrayList<CarEntity>();

        try {
            String sql = "select e.* from car e order by e.id";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CarEntity entity = new CarEntity();
                entity.setId(rs.getLong("id"));
                entity.setBrand(rs.getString("brand"));
                entity.setModel(rs.getString("model"));
                entity.setPrice(rs.getDouble("price"));
                entity.setStatus(rs.getString("status"));
                carList.add(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
            error = e.toString();
        }

        request.setAttribute("error", error);
        request.setAttribute("carList", carList);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/car_catalog_admin.jsp");
        dispatcher.forward(request, response);
    }

}
