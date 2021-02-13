package ua.finalproject.controller.commands;

import ua.finalproject.model.entity.CartDTO;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.CartService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RemoveCartItem implements Command {
    private final CartService cartService;
    private final Logger LOGGER = Logger.getLogger(RemoveCartItem.class.getName());

    public RemoveCartItem(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            int itemId = Integer.parseInt(request.getParameter("id"));
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                cartService.deleteItem(itemId);
            }
            CartDTO cart = (CartDTO) request.getSession().getAttribute("cart");

            cart.getItems()
                    .stream()
                    .filter(e -> e.getId() == itemId)
                    .findAny()
                    .ifPresent(e -> {
                        cart.getItems().remove(e);
                        cart.setTotalPrice(cart.getTotalPrice() - e.getProduct().getPrice());
                    });
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return Pages.REDIRECT_CART_PAGE;
    }
}
