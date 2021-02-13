package ua.finalproject.model.dao.mapper;

import ua.finalproject.model.entity.RoleType;
import ua.finalproject.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .setId(rs.getInt("id"))
                .setFirstName(rs.getString("firstName"))
                .setLastName(rs.getString("lastName"))
                .setEmail(rs.getString("email"))
                .setPassword(rs.getString("password"))
                .setRole(RoleType.valueOf(rs.getString("role")))
                .build();
    }
}
