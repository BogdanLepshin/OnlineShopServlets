package ua.finalproject.model.dao.mapper;

import ua.finalproject.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements ObjectMapper<Category> {
    @Override
    public Category extractFromResultSet(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setNameRu(rs.getString("name_ru"));
        category.setNameEn(rs.getString("name_en"));
        return category;
    }
}
