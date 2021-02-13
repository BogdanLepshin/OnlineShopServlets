package ua.finalproject.model.dao.impl;

import ua.finalproject.model.dao.CategoryDao;
import ua.finalproject.model.dao.mapper.CategoryMapper;
import ua.finalproject.model.entity.Category;
import ua.finalproject.model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCategoryDao implements CategoryDao {
    private final Connection connection;

    public JDBCCategoryDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Category category) throws SQLException {

    }

    @Override
    public Category findById(int id) {
        final String query = "select * from category where id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            CategoryMapper categoryMapper = new CategoryMapper();

            if (rs.next()) {
                return categoryMapper
                        .extractFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();

        final String query = "select * from category";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            CategoryMapper categoryMapper = new CategoryMapper();

            while (rs.next()) {
                categories.add(categoryMapper
                        .extractFromResultSet(rs));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category update(Category entity) {
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
}
