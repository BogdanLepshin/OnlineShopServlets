package ua.finalproject.controller.commands;

import ua.finalproject.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CancelOrder implements Command {

    private final OrderService orderService;
    private final Logger logger = Logger.getLogger(OrderDetailsPage.class.getName());

    public CancelOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final int ORDER_STATUS_CANCELED = 2;
        try {
            orderService.changeOrderStatus(Integer.parseInt(request.getParameter("id")), ORDER_STATUS_CANCELED);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        return Pages.REDIRECT_ORDERS_PAGE;
    }
}
