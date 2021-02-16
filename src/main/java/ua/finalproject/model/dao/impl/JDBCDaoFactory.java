package ua.finalproject.model.dao.impl;

import ua.finalproject.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ProductDao createProductDao() {
        return new JDBCProductDao(getConnection());
    }

    @Override
    public CategoryDao createCategoryDao() {
        return new JDBCCategoryDao(getConnection());
    }

    @Override
    public BrandDao createBrandDao() {
        return new JDBCBrandDao(getConnection());
    }

    @Override
    public CartDao createCartDao() {
        return new JDBCCartDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    @Override
    public OrderDetailsDao createOrderDetailsDao() {
        return new JDBCOrderDetailsDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
