package ua.finalproject.model.dao.impl;

import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.mapper.OrderMapper;
import ua.finalproject.model.entity.CartDTO;
import ua.finalproject.model.entity.CartItem;
import ua.finalproject.model.entity.OrderDTO;
import ua.finalproject.model.entity.OrderStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCOrderDao implements OrderDao {
    private final Connection connection;
    private final Logger LOGGER = Logger.getLogger(JDBCOrderDao.class.getName());

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(OrderDTO entity) throws SQLException {
    }

    @Override
    public OrderDTO findById(int id) {
        final String query = "SELECT o.id, o.user_id, o.creation_date, o.completion_date, o.total_price, " +
                "os.id, os.name_ru, os.name_en, " +
                "pt.id, pt.payment_type_ru, pt.payment_type_en, " +
                "d.id, d.type_ru, d.type_en, " +
                "od.id, od.order_id, od.product_id, od.amount, " +
                "p.id, p.image, p.name, p.price " +
                "FROM orders o " +
                "JOIN order_details od on od.order_id = o.id " +
                "JOIN product p on od.product_id = p.id " +
                "JOIN delivery d on d.id = o.delivery_id " +
                "JOIN order_status os on os.id = o.status_id " +
                "JOIN payment pt on pt.id = o.payment_id " +
                "WHERE o.id=?";


        try (PreparedStatement st = connection.prepareStatement(query)) {
            OrderMapper orderMapper = new OrderMapper();
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            return orderMapper.extractOrderWithOrderDetailsFromResultSet(rs);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> products = new ArrayList<>();

        final String query = "SELECT o.id, o.user_id, o.creation_date, o.completion_date, o.status_id, o.total_price, " +
                "os.id, os.name_ru, os.name_en, " +
                "pt.id, pt.payment_type_ru, pt.payment_type_en, " +
                "d.id, d.type_en, d.type_ru " +
                "FROM orders o " +
                "JOIN delivery d on d.id = o.delivery_id " +
                "JOIN order_status os on os.id = o.status_id " +
                "JOIN payment pt on pt.id = o.payment_id " +
                "ORDER BY o.creation_date DESC";

        try (Statement st = connection.createStatement()) {
            OrderMapper orderMapper = new OrderMapper();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                products.add(orderMapper.extractFromResultSet(rs));
            }
            return products;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public OrderDTO update(OrderDTO entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createOrderFromCart(CartDTO cartDTO) {
        final String sql = "INSERT INTO orders (user_id, creation_date, payment_id, delivery_id, status_id, total_price) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        final String sql2 = "INSERT INTO order_details (order_id, product_id, amount) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement st2 = connection.prepareStatement(sql2)) {
            connection.setAutoCommit(false);

            st.setInt(1, cartDTO.getUserId());
            st.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            st.setInt(3, 1);
            st.setInt(4, 1);
            st.setInt(5, 1);
            st.setInt(6, cartDTO.getTotalPrice());
            st.executeUpdate();
            connection.commit();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                for (CartItem item : cartDTO.getItems()) {
                    st2.setInt(1, rs.getInt(1));
                    st2.setInt(2, item.getProduct().getId());
                    st2.setInt(3, item.getAmount());
                    st2.executeUpdate();
                }
            }
            connection.commit();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                LOGGER.log(Level.SEVERE, throwables.getMessage(), throwables);
            }
        }
    }

    @Override
    public List<OrderDTO> findOrdersByUserId(int id) {
        final String query = "SELECT o.id, o.user_id, o.creation_date, o.completion_date, o.total_price, " +
                "os.id, os.name_ru, os.name_en, " +
                "pt.id, pt.payment_type_ru, pt.payment_type_en, " +
                "d.id, d.type_ru, d.type_en, " +
                "od.id, od.order_id, od.product_id, od.amount, " +
                "p.id, p.image, p.name, p.price " +
                "FROM orders o " +
                "JOIN order_details od on od.order_id = o.id " +
                "JOIN product p on od.product_id = p.id " +
                "JOIN delivery d on d.id = o.delivery_id " +
                "JOIN order_status os on os.id = o.status_id " +
                "JOIN payment pt on pt.id = o.payment_id " +
                "WHERE user_id=? ORDER BY o.creation_date DESC";


        try (PreparedStatement st = connection.prepareStatement(query)) {
            OrderMapper orderMapper = new OrderMapper();
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            return orderMapper.extractOrderListFromRs(rs);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void updateStatus(int orderId, int statusId) {
        final String query = "UPDATE orders SET status_id=?, completion_date=? WHERE id=?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, statusId);
            st.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            st.setInt(3, orderId);
            st.executeUpdate();
            LOGGER.info("Number of updated rows: " + st.executeUpdate());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public List<OrderStatus> findAllStatuses() {
        List<OrderStatus> statusList = new ArrayList<>();

        final String query = "SELECT * FROM order_status";

        try (Statement st = connection.createStatement()) {
            OrderMapper orderMapper = new OrderMapper();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                statusList.add(orderMapper.extractStatusFromRs(rs));
            }
            return statusList;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

}
