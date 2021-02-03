package ua.finalproject.model.service;

import ua.finalproject.model.dao.DaoFactory;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers(){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public User findUserByEmailAndPassword(String email, String password) {
        return daoFactory.createUserDao().findByEmailAndPassword(email, password);
    }

    public void save(User user) throws SQLException {
        daoFactory.createUserDao().create(user);
    }

    public User findUserByEmail(String email) {
        return daoFactory.createUserDao().findByEmail(email);
    }
}
