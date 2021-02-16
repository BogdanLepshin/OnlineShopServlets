package ua.finalproject.model.service;

import ua.finalproject.controller.exceptions.DBException;
import ua.finalproject.model.dao.DaoFactory;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.impl.JDBCOrderDao;
import ua.finalproject.model.entity.CartDTO;
import ua.finalproject.model.entity.OrderDTO;
import ua.finalproject.model.entity.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public List<OrderDTO> getOrderByUserId(int id) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return Optional.ofNullable(orderDao.findOrdersByUserId(id)).orElse(new ArrayList<>());
        }
    }

    public void addOrderFromCart(CartDTO cartDTO) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            orderDao.createOrderFromCart(cartDTO);
        }
    }

    public OrderDTO getOrderById(int id) throws DBException {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return Optional.ofNullable(orderDao.findById(id))
                    .orElseThrow(() -> new DBException("There is no such order with id: " + id));
        }
    }

    public void changeOrderStatus(int id, int status) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            orderDao.updateStatus(id, status);
        }
    }

    public List<OrderDTO> getAllOrders() {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return Optional.ofNullable(orderDao.findAll()).orElse(new ArrayList<>());
        }
    }

    public List<OrderStatus> getAllStatuses() {
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            return Optional.ofNullable(orderDao.findAllStatuses()).orElse(new ArrayList<>());
        }
    }
}
