package ua.finalproject.model.dao;

import ua.finalproject.model.entity.User;

import java.util.List;

public interface UserDao extends GeneralDao<User> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    List<User> findUserByRole(String role);
}
