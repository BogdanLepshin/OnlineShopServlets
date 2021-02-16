package ua.finalproject.controller.commands.admin;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.commands.Orders;
import ua.finalproject.controller.commands.Pages;
import ua.finalproject.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class OrdersManager implements Command {
    private final OrderService orderService;

    private final Logger logger = Logger.getLogger(Orders.class.getName());

    public OrdersManager(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders", orderService.getAllOrders());

        return Pages.FORWARD_ORDERS_MANAGER_PAGE;
    }
}
