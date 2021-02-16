package ua.finalproject.model.dao;

import ua.finalproject.model.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsDao extends GenericDao<OrderDetails>{
    List<OrderDetails> findOrderDetailsByOrderId(int id);
    List<OrderDetails> findOrderDetailsByUserId(int id);
}
