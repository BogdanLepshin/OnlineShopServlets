package ua.finalproject.model.dao;

import ua.finalproject.model.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends GenericDao<Product> {
    List<Product> findProductsByCategory(int category);
    List<Product> findProductByCategoryPageable(int start, int limit, int categoryId);
    Product save(Product product) throws SQLException;
    void updateStatus(int id, boolean b);
}
