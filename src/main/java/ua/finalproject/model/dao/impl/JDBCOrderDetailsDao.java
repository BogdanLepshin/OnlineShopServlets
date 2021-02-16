package ua.finalproject.model.dao.impl;

import ua.finalproject.model.dao.OrderDetailsDao;
import ua.finalproject.model.dao.mapper.OrderDetailsMapper;
import ua.finalproject.model.dao.mapper.OrderMapper;
import ua.finalproject.model.entity.OrderDTO;
import ua.finalproject.model.entity.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCOrderDetailsDao implements OrderDetailsDao {

    private final Connection connection;

    private final Logger log = Logger.getLogger(JDBCOrderDetailsDao.class.getName());

    public JDBCOrderDetailsDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(OrderDetails entity) throws SQLException {

    }

    @Override
    public OrderDetails findById(int id) {
        return null;
    }

    @Override
    public List<OrderDetails> findAll() {
        return null;
    }

    @Override
    public OrderDetails update(OrderDetails entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public List<OrderDetails> findOrderDetailsByOrderId(int id) {
        return null;
    }

    @Override
    public List<OrderDetails> findOrderDetailsByUserId(int id) {
        List<OrderDetails> orderDetails = new ArrayList<>();

        final String query = "SELECT od.id, od.order_id,  od.amount, p.id, p.image, p.name, p.price " +
                "FROM order_details od " +
                "Join orders o on o.id=od.order_id " +
                "Join product p on p.id=od.product_id " +
                "where user_id=?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            OrderDetailsMapper orderDetailsMapper = new OrderDetailsMapper();
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                orderDetails.add(orderDetailsMapper.extractFromResultSet(rs));
            }
            return orderDetails;
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
}
