package ua.finalproject.controller.commands.admin;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.commands.Orders;
import ua.finalproject.controller.commands.Pages;
import ua.finalproject.controller.exceptions.DBException;
import ua.finalproject.model.entity.OrderDTO;
import ua.finalproject.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeOrderStatus implements Command {
    private final OrderService orderService;

    private final Logger logger = Logger.getLogger(Orders.class.getName());

    public ChangeOrderStatus(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int orderStatusId = Integer.parseInt(request.getParameter("statusId"));
            orderService.changeOrderStatus(orderId, orderStatusId);

            OrderDTO orderDTO = orderService.getOrderById(Integer.parseInt(request.getParameter("orderId")));
            request.setAttribute("order", orderDTO);
            request.setAttribute("statuses", orderService.getAllStatuses());
        } catch (NumberFormatException | DBException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return Pages.FORWARD_ADMIN_ORDER_DETAILS_PAGE;
    }
}
