package servlet.user;

import database.ConnectionUtils;
import entity.CarEntity;
import entity.OrderEntity;

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
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
                    "and c.id not in (select o.car_id from orders o where o.status in ('pending','approved','delivered'))");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            OrderEntity orderEntity = new OrderEntity();
            StringBuilder errors = new StringBuilder();
            orderEntity.setCarId(Long.valueOf(request.getParameter("car")));
            orderEntity.setUserId((Long)(request.getSession().getAttribute("userId")));

            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            if (request.getParameter("dateFrom").equals("")) {
                errors.append("date From is null<br>");
            } else {
                orderEntity.setDateFrom(LocalDate.parse(request.getParameter("dateFrom"), dtf));
            }

            if (request.getParameter("dateTo").equals("")) {
                errors.append("date To is null<br>");
            } else {
                orderEntity.setDateTo(LocalDate.parse(request.getParameter("dateTo"), dtf));
            }

            if (request.getParameter("passport").equals("")) {
                errors.append("passport is null<br>");
            } else {
                orderEntity.setPassport(request.getParameter("passport"));
            }

            if (errors.length() > 0) {
                request.setAttribute("error", errors.toString());
            } else {
                try {
                    Connection connection = ConnectionUtils.getConnection();
                    String sql = "select price from car where id = ?";
                    PreparedStatement pr = connection.prepareStatement(sql);
                    pr.setLong(1, orderEntity.getCarId());
                    ResultSet rs = pr.executeQuery();
                    if (rs.next()) {
                        int days = Period.between(orderEntity.getDateFrom(), orderEntity.getDateTo()).getDays() + 1;
                        orderEntity.setTotalAmount(days * rs.getDouble("price"));
                    }

                    sql = "Insert into orders(user_id, car_id, status, created, date_from, date_to, passport, total_amount) values (?,?,?,?,?,?,?,?)";
                        pr = connection.prepareStatement(sql);
                    pr.setLong(1, orderEntity.getUserId());
                    pr.setLong(2, orderEntity.getCarId());
                    pr.setString(3, "pending");
                    pr.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
                    pr.setDate(5, java.sql.Date.valueOf(orderEntity.getDateFrom()));
                    pr.setDate(6, java.sql.Date.valueOf(orderEntity.getDateTo()));
                    pr.setString(7, orderEntity.getPassport());
                    pr.setDouble(8, orderEntity.getTotalAmount());

                    pr.executeUpdate();
                    request.setAttribute("result", "Order saved, total amount: " + orderEntity.getTotalAmount());

                    pr.close();
                    connection.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    errors.append(e);
                }

            }

            RequestDispatcher dispatcher;

            if (errors.length() > 0) {
                request.setAttribute("error", errors.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/user/user_new_order_error.jsp");
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/user/user_new_order_result.jsp");
            }

            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
