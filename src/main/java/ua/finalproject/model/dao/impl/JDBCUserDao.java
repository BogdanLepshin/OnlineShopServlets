package ua.finalproject.model.dao.impl;

import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.dao.mapper.UserMapper;
import ua.finalproject.model.entity.Product;
import ua.finalproject.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JDBCUserDao implements UserDao {
    private final Connection connection;
    private Logger log = Logger.getLogger(JDBCProductDao.class.getName());

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) throws SQLException {
        final String sql = "INSERT INTO user (firstName, lastName, email, password, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole().toString());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("User hasn't added");
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        final String query = "select * from user";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                users.add(userMapper
                        .extractFromResultSet(rs));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        final String query = "select * from user where email=? and password=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, email);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            UserMapper userMapper = new UserMapper();

            if (rs.next()) {
                return userMapper
                        .extractFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        final String query = "select * from user where email=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            UserMapper userMapper = new UserMapper();

            if (rs.next()) {
                return userMapper
                        .extractFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findUserByRole(String role) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USER WHERE ROLE=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, role);

            ResultSet rs = st.executeQuery();
            UserMapper userMapper = new UserMapper();

            if (rs.next()) {
                users.add(userMapper
                        .extractFromResultSet(rs));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
            log.info(this.getClass().getName() + " " + connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
