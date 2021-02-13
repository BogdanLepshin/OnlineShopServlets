package ua.finalproject.controller.commands;

import ua.finalproject.model.entity.*;
import ua.finalproject.model.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CartPage implements Command {

    private final CartService cartService;

    public CartPage(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CartDTO cartDTO = (CartDTO) Optional.ofNullable(request.getSession().getAttribute("cart")).orElse(new CartDTO());
        session.setAttribute("cart", cartDTO);

        List<CartItem> productsInCart = cartDTO.getItems();

        if (isUserAuthenticatedAndCartNotMerged(request, user)) {
            cartDTO.setUserId(user.getId());
            CartDTO userCartDTO = cartService.getCartByUserId(user.getId());
            if (isCartNotEmpty(cartDTO)) {
                cartService.addCart(cartDTO);
            }
            productsInCart.addAll(userCartDTO.getItems());

            cartDTO.setTotalPrice(userCartDTO.getTotalPrice()+cartDTO.getTotalPrice());
            session.setAttribute("isMerged", true);
        }
        return Pages.FORWARD_CART_PAGE;
    }

    private boolean isCartNotEmpty(CartDTO cartDTO) {
        return !cartDTO.getItems().isEmpty();
    }

    private boolean isUserAuthenticatedAndCartNotMerged(HttpServletRequest request, User user) {
        return user != null && isNotMerged(request);
    }

    private boolean isNotMerged(HttpServletRequest request) {
        return request.getSession().getAttribute("isMerged") == null
                || !(boolean) request.getSession().getAttribute("isMerged");
    }


}
