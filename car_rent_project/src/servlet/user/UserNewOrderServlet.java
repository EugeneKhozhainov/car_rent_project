package servlet.user;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/userNewOrder"})
public class UserNewOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionUtils.getConnection();

        try {
            List<CarEntity> carList = new ArrayList<CarEntity>();

            PreparedStatement statement = connection.prepareStatement("select c.* from car c where c.status = ? " +
                    "and c.id not in (select o.car_id from orders o where o.status not in ('pending','approved','delivered'))");
            statement.setString(1, "working");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CarEntity entity = new CarEntity();
                entity.setBrand(rs.getString("brand"));
                entity.setId(rs.getLong("id"));
                entity.setModel(rs.getString("model"));
                entity.setPrice(rs.getDouble("price"));
                entity.setStatus(rs.getString("status"));
                carList.add(entity);
            }
            rs.close();

            request.setAttribute("carList", carList);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/user/user_new_order.jsp");
        dispatcher.forward(request, response);
    }

}
