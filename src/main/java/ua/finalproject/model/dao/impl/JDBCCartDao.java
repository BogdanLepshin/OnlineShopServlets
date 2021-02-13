package ua.finalproject.model.dao.impl;

import ua.finalproject.model.dao.CartDao;
import ua.finalproject.model.dao.mapper.CartMapper;
import ua.finalproject.model.entity.CartDTO;
import ua.finalproject.model.entity.CartItem;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCCartDao implements CartDao {

    private final Connection connection;
    private final Logger LOGGER = Logger.getLogger(JDBCCartDao.class.getName());

    public JDBCCartDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CartDTO findCartByUserId(int id) {
        final String query = "SELECT c.id, c.user_id, i.id, i.amount, c.total_price, " +
                "p.id, p.name, p.image, p.price " +
                "FROM cart c JOIN cart_item i on item_id = i.id JOIN product p ON i.product_id = p.id " +
                "WHERE c.user_id=?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            CartMapper cartMapper = new CartMapper();
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            return cartMapper.extractFromResultSet(rs);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void create(CartDTO entity) throws SQLException {
        final String sql = "INSERT INTO cart_item ( product_id, amount) VALUES (?, ?)";
        final String sql2 = "INSERT INTO cart (user_id, item_id, total_price) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
             PreparedStatement st2 = connection.prepareStatement(sql2)) {
            connection.setAutoCommit(false);

            for (CartItem item : entity.getItems()) {
                st.setInt(1, item.getProduct().getId());
                st.setInt(2, item.getAmount());
                st.executeUpdate();
            }
            connection.commit();
            ResultSet rs = st.getGeneratedKeys();
            for (CartItem item : entity.getItems()) {
                if (rs.next())
                    item.setId(rs.getInt(1));
                st2.setInt(1, entity.getUserId());
                st2.setInt(2, item.getId());
                st2.setInt(3, entity.getTotalPrice());
                st2.executeUpdate();
            }
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public CartDTO findById(int id) {
        return null;
    }

    @Override
    public List<CartDTO> findAll() {
        return null;
    }

    @Override
    public CartDTO update(CartDTO entity) {
        return null;
    }


    @Override
    public void delete(int id) {
        final String query = "DELETE FROM cart WHERE item_id=?";
        final String query2 = "DELETE FROM cart_item WHERE id=?";

        try (PreparedStatement st = connection.prepareStatement(query);
             PreparedStatement st2 = connection.prepareStatement(query2)) {
            connection.setAutoCommit(false);
            st.setInt(1, id);
            st2.setInt(1, id);
            int rowsDeleted = 0;
            rowsDeleted+=st.executeUpdate();
            rowsDeleted+=st2.executeUpdate();
            connection.commit();
            LOGGER.info("Number of deleted rows: " + rowsDeleted);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                LOGGER.log(Level.SEVERE, throwables.getMessage(), throwables);
            }
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
