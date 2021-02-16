package ua.finalproject.model.service;

import ua.finalproject.model.dao.DaoFactory;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers(){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public User findUserByEmailAndPassword(String email, String password) {
        try (UserDao dao = daoFactory.createUserDao()){
            return dao.findByEmailAndPassword(email, password);
        }
    }

    public void save(User user) throws SQLException {
        try (UserDao dao = daoFactory.createUserDao()){
            dao.create(user);
        }
    }

    public User findUserByEmail(String email) {
        try (UserDao dao = daoFactory.createUserDao()){
            return dao.findByEmail(email);
        }
    }

    public User getUserById(int userId) throws SQLException {
        try (UserDao dao = daoFactory.createUserDao()){
            return dao.findById(userId);
        }
    }
}
