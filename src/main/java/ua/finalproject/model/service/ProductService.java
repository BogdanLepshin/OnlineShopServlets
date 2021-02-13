package ua.finalproject.model.service;

import ua.finalproject.controller.exceptions.DBException;
import ua.finalproject.model.dao.DaoFactory;
import ua.finalproject.model.dao.ProductDao;
import ua.finalproject.model.entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<List<Product>> getAllProducts() {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return Optional.ofNullable(productDao.findAll());
        }
    }

    public Optional<List<Product>> getProductsPageable(int start, int limit, int category) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return Optional.ofNullable(productDao.findProductByCategoryPageable(start, limit, category));
        }
    }

    public Product getProductById(int id) throws DBException {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return Optional.ofNullable(productDao.findById(id)).orElseThrow(() -> new DBException("Product not found"));
        }
    }

    public Optional<List<Product>> getProductsByCategory(int id) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return Optional.ofNullable(productDao.findProductsByCategory(id));
        }
    }

    public Product saveProduct(Product product) throws SQLException {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.save(product);
        }
    }

    public void deleteProduct(int id) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.delete(id);
        }
    }

    public Product updateProduct(Product product) throws DBException {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return Optional.ofNullable(productDao.update(product)).orElseThrow(() -> new DBException("Product not found"));
        }
    }

    public void updateStatus(int id, boolean b) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.updateStatus(id, b);
        }
    }
}
