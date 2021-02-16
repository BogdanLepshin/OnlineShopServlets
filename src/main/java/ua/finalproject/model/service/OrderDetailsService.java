package ua.finalproject.model.service;

import ua.finalproject.model.dao.CartDao;
import ua.finalproject.model.dao.DaoFactory;
import ua.finalproject.model.dao.OrderDetailsDao;
import ua.finalproject.model.entity.CartDTO;
import ua.finalproject.model.entity.OrderDetails;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class OrderDetailsService {
    private static final Logger LOGGER = Logger.getLogger(OrderDetailsService.class.getName());
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<List<OrderDetails>> getOrderDetailsByUserId(int id) {
        try(OrderDetailsDao orderDetailsDao = daoFactory.createOrderDetailsDao()) {
            return Optional.ofNullable(orderDetailsDao.findOrderDetailsByUserId(id));
        }
    }

}
