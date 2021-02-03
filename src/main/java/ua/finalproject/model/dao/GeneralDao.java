package ua.finalproject.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GeneralDao<T> extends AutoCloseable {
    void create (T entity) throws SQLException;
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
