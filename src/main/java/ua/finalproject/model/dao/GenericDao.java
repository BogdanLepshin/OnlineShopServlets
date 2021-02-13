package ua.finalproject.model.dao;

import ua.finalproject.model.entity.Product;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    void create (T entity) throws SQLException;
    T findById(int id);
    List<T> findAll();
    T update(T entity);
    void delete(int id);
    void close();
}
