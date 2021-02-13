package ua.finalproject.controller.commands;

import ua.finalproject.controller.exceptions.DBException;
import ua.finalproject.model.entity.CartDTO;
import ua.finalproject.model.entity.CartItem;
import ua.finalproject.model.entity.Product;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.CartService;
import ua.finalproject.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddToCart implements Command {
    private final ProductService productService;
    private final CartService cartService;
    private final Logger LOGGER = Logger.getLogger(AddToCart.class.getName());

    public AddToCart(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        CartDTO cartDTO = Optional.ofNullable((CartDTO) session.getAttribute("cart")).orElse(new CartDTO());
        try {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int quantityParam = Integer.parseInt(request.getParameter("quantity"));

            List<CartItem> items = cartDTO.getItems();
            Product product = productService.getProductById(productId);
            CartItem newCartItem = new CartItem(product, quantityParam);
            items.add(newCartItem);

            int totalPrice = cartDTO.getTotalPrice()+product.getPrice()*quantityParam;
            cartDTO.setTotalPrice(totalPrice);

            User user = (User) session.getAttribute("user");
            if (isNotGuest(user)) {
                CartDTO newCartDTO = new CartDTO();
                newCartDTO.getItems().add(newCartItem);
                newCartDTO.setUserId(user.getId());
                cartService.addCart(newCartDTO);
            }
            session.setAttribute("cart", cartDTO);
        } catch (NumberFormatException | DBException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return Pages.FORWARD_PRODUCT_DETAILS;
        }
        return Pages.REDIRECT_CART_PAGE;
    }

    private boolean isNotGuest(User user) {
        return user != null;
    }
}
