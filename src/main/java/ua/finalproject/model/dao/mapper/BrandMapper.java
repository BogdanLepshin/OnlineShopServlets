package ua.finalproject.model.dao.mapper;

import ua.finalproject.model.entity.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper implements ObjectMapper<Brand>{
    @Override
    public Brand extractFromResultSet(ResultSet rs) throws SQLException {
        Brand brand = new Brand();
        brand.setId(rs.getInt("id"));
        brand.setName(rs.getString("name"));
        return brand;
    }
}
