package ua.finalproject.model.dao.impl;

import ua.finalproject.model.dao.BrandDao;
import ua.finalproject.model.dao.mapper.BrandMapper;
import ua.finalproject.model.entity.Brand;
import ua.finalproject.model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCBrandDao implements BrandDao {
    private final Connection connection;

    public JDBCBrandDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Brand entity) throws SQLException {

    }

    @Override
    public Brand findById(int id) {
        final String query = "select * from brand where id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            BrandMapper brandMapper = new BrandMapper();

            if (rs.next()) {
                return brandMapper
                        .extractFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> brands = new ArrayList<>();

        final String query = "select * from brand";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            BrandMapper brandMapper = new BrandMapper();

            while (rs.next()) {
                brands.add(brandMapper
                        .extractFromResultSet(rs));
            }
            return brands;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Brand update(Brand entity) {
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
