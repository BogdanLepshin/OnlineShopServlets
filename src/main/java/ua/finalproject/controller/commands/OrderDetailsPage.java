package ua.finalproject.controller.commands;

import ua.finalproject.controller.exceptions.DBException;
import ua.finalproject.model.entity.OrderDTO;
import ua.finalproject.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailsPage implements Command {
    private final OrderService orderService;
    private final Logger logger = Logger.getLogger(OrderDetailsPage.class.getName());

    public OrderDetailsPage(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            OrderDTO orderDTO = orderService.getOrderById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("order", orderDTO);
        } catch (NumberFormatException | DBException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return Pages.FORWARD_ORDER_DETAILS_PAGE;
    }
}
