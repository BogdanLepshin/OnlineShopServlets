package ua.finalproject.model.service;

import ua.finalproject.model.dao.CartDao;
import ua.finalproject.model.dao.DaoFactory;
import ua.finalproject.model.entity.CartDTO;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartService {
    private static final Logger LOGGER = Logger.getLogger(CartService.class.getName());
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public CartDTO getCartByUserId(int id) {
        try(CartDao cartDao = daoFactory.createCartDao()) {
            return Optional.ofNullable(cartDao.findCartByUserId(id)).orElse(new CartDTO());
        }
    }

    public void addCart(CartDTO cartDTO) {
        try(CartDao cartDao = daoFactory.createCartDao()) {
            cartDao.create(cartDTO);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void deleteItem(int itemId) {
        try(CartDao cartDao = daoFactory.createCartDao()) {
            cartDao.delete(itemId);
        }
    }
}
