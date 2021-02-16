package ua.finalproject.controller.commands;

import ua.finalproject.controller.exceptions.DBException;
import ua.finalproject.model.entity.OrderDTO;
import ua.finalproject.model.entity.OrderDetails;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.OrderDetailsService;
import ua.finalproject.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Orders implements Command {
    private final OrderService orderService;

    private final Logger logger = Logger.getLogger(Orders.class.getName());

    public Orders(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        List<OrderDTO> ordersList = orderService.getOrderByUserId(user.getId());
        request.setAttribute("orders", ordersList);

        return Pages.FORWARD_ORDERS_PAGE;
    }
}
