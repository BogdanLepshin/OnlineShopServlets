package ua.finalproject.model.dao.mapper;

import ua.finalproject.model.entity.Brand;
import ua.finalproject.model.entity.Category;
import ua.finalproject.model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ObjectMapper<Product> {
    @Override
    public Product extractFromResultSet(ResultSet rs) throws SQLException {

        Category category = new Category();
        category.setId(rs.getInt("c.id"));
        category.setNameRu(rs.getString("c.name_ru"));
        category.setNameEn(rs.getString("c.name_en"));
        Brand brand = new Brand();
        brand.setId(rs.getInt("b.id"));
        brand.setName(rs.getString("b.name"));

        return new Product.Builder()
                .setId(rs.getInt("p.id"))
                .setName(rs.getString("p.name"))
                .setImage(rs.getString("p.image"))
                .setCategory(category)
                .setBrand(brand)
                .setDescriptionRu(rs.getString("description_ru"))
                .setDescriptionEn(rs.getString("description_en"))
                .setPrice(rs.getInt("price"))
                .setEnabled(rs.getBoolean("enabled"))
                .build();
    }
}
