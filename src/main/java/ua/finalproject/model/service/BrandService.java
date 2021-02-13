package ua.finalproject.model.service;

import ua.finalproject.model.dao.BrandDao;
import ua.finalproject.model.dao.DaoFactory;
import ua.finalproject.model.entity.Brand;
import ua.finalproject.model.entity.Category;

import java.util.List;
import java.util.Optional;

public class BrandService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<List<Brand>> getAllBrands() {
        try (BrandDao brandDao = daoFactory.createBrandDao()) {
            return Optional.ofNullable(brandDao.findAll());
        }
    }

    public Optional<Brand> getBrandById(int id) {
        try (BrandDao brandDao = daoFactory.createBrandDao()) {
            return Optional.ofNullable(brandDao.findById(id));
        }

    }
}
