package ua.finalproject.model.service;

import ua.finalproject.model.dao.CategoryDao;
import ua.finalproject.model.dao.DaoFactory;
import ua.finalproject.model.entity.Category;

import java.util.List;
import java.util.Optional;

public class CategoryService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<List<Category>> getAllCategories() {
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            return Optional.ofNullable(categoryDao.findAll());
        }

    }

    public Optional<Category> getCategoryById(int id) {
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            return Optional.ofNullable(categoryDao.findById(id));
        }
    }
}
