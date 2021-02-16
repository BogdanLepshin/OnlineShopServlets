package ua.finalproject.model.dao;

import ua.finalproject.model.entity.CartDTO;
import ua.finalproject.model.entity.OrderDTO;

import java.util.List;

public interface OrderDao extends GenericDao<OrderDTO>{
    void createOrderFromCart(CartDTO cartDTO);
    List<OrderDTO> findOrdersByUserId(int id);
    void updateStatus(int orderId, int statusId);
}
