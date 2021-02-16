package ua.finalproject.model.dao.impl;

import ua.finalproject.model.dao.ProductDao;
import ua.finalproject.model.dao.mapper.ProductMapper;
import ua.finalproject.model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCProductDao implements ProductDao {

    private final Logger LOGGER = Logger.getLogger(JDBCProductDao.class.getName());
    private final Connection connection;

    public JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Product product) throws SQLException {
        final String sql = "INSERT INTO product (name, image, category_id, brand_id, " +
                "description_ru, description_en, price, enabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            setValues(product, st);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new SQLException("User hasn't been added");
        }
    }

    @Override
    public List<Product> findProductsByCategory(int category) {
        List<Product> users = new ArrayList<>();

        final String query = "SELECT p.id, p.name, p.image, c.id, c.name_ru, c.name_en, b.id, b.name, description_en, description_ru, price, enabled\n" +
                "FROM product p join category c on p.category_id = c.id join brand b on p.brand_id = b.id where c.id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, category);
            ResultSet rs = st.executeQuery();

            ProductMapper productMapper = new ProductMapper();

            while (rs.next()) {
                users.add(productMapper
                        .extractFromResultSet(rs));
            }
            return users;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Product> findProductByCategoryPageable(int start, int limit, int categoryId) {
        List<Product> users = new ArrayList<>();

        final String query = "SELECT p.id, p.name, p.image, c.id, c.name_ru, c.name_en, b.id, b.name, description_en, description_ru, price, enabled\n" +
                "FROM product p join category c on p.category_id = c.id join brand b on p.brand_id = b.id where enabled=? and p.category_id=? LIMIT ?,? ";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setBoolean(1, true);
            st.setInt(2, categoryId);
            st.setInt(3, start * limit - limit);
            st.setInt(4, limit);
            ResultSet rs = st.executeQuery();

            ProductMapper productMapper = new ProductMapper();

            while (rs.next()) {
                users.add(productMapper
                        .extractFromResultSet(rs));
            }
            return users;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Product save(Product product) throws SQLException {
        final String sql = "INSERT INTO product (name, image, category_id, brand_id, " +
                "description_ru, description_en, price, enabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setValues(product, st);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            product.setId(generatedKey);
            return product;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new SQLException("User hasn't been added");
        }
    }

    @Override
    public void updateStatus(int id, boolean status) {
       final String query = "UPDATE product SET enabled=? WHERE id=?";

        try (PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            st.setBoolean(1, status);
            st.setInt(2, id);
            st.executeUpdate();
            LOGGER.info("Number of updated rows: " + st.executeUpdate());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public Product findById(int id) {
        final String query = "SELECT p.id, p.name, p.image, c.id, c.name_ru, c.name_en, b.id, b.name, description_en, description_ru, price, enabled\n" +
                "FROM product p join category c on p.category_id = c.id join brand b on p.brand_id = b.id where p.id=?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            ProductMapper productMapper = new ProductMapper();
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return productMapper.extractFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        final String query = "SELECT p.id, p.name, p.image, c.id, c.name_ru, c.name_en, b.id, b.name, description_en, description_ru, price, enabled\n" +
                "FROM product p join category c on p.category_id = c.id join brand b on p.brand_id = b.id";

        try (Statement st = connection.createStatement()){
            ProductMapper productMapper = new ProductMapper();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                products.add(productMapper.extractFromResultSet(rs));
            }
            return products;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Product update(Product product) {
        final String query = "UPDATE product SET name=?, image=?, " +
                "category_id=?, brand_id=?, description_ru=?, description_en=?, price=?, enabled=? WHERE id=?";

        try (PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setValues(product, st);
            st.setInt(9, product.getId());
            st.executeUpdate();
            LOGGER.info("Number of updated rows: " + st.executeUpdate());
            return product;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    private void setValues(Product product, PreparedStatement st) throws SQLException {
        st.setString(1, product.getName());
        st.setString(2, product.getImage());
        st.setInt(3, product.getCategory().getId());
        st.setInt(4, product.getBrand().getId());
        st.setString(5, product.getDescriptionRu());
        st.setString(6, product.getDescriptionEn());
        st.setInt(7, product.getPrice());
        st.setBoolean(8, product.isEnabled());
    }

    @Override
    public void delete(int id) {
        final String query = "DELETE FROM product WHERE id=?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            LOGGER.info("Number of deleted rows: " + st.executeUpdate());
        } catch (SQLException e) {
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
