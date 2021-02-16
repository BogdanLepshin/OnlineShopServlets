package ua.finalproject.controller.commands;

import ua.finalproject.controller.exceptions.DBException;
import ua.finalproject.model.entity.CartDTO;
import ua.finalproject.model.entity.OrderDTO;
import ua.finalproject.model.entity.OrderDetails;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.CartService;
import ua.finalproject.model.service.OrderService;
import ua.finalproject.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MakeOrder implements Command {
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;
    private final Logger LOGGER = Logger.getLogger(MakeOrder.class.getName());

    public MakeOrder(OrderService orderService, CartService cartService, UserService userService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        CartDTO cartDTO;
        int userId;
        try {
            userId = Integer.parseInt(request.getParameter("userId"));
            cartDTO = cartService.getCartByUserId(userId).orElseThrow(() -> new DBException("There is no such cart"));
        } catch (NumberFormatException | DBException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return Pages.REDIRECT_CART_PAGE;
        }
        if (request.getMethod().equals("GET")) {
            request.setAttribute("cartData", cartDTO);
            return Pages.FORWARD_MAKE_ORDER_PAGE;
        }
        orderService.addOrderFromCart(cartDTO);

        return Pages.REDIRECT_ORDERS_PAGE;
    }
}
