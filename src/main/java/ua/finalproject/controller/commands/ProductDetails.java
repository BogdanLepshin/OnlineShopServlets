package ua.finalproject.controller.commands;

import ua.finalproject.controller.exceptions.DBException;
import ua.finalproject.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDetails implements Command {
    private final ProductService service;
    private final Logger LOGGER = Logger.getLogger(ProductDetails.class.getName());

    public ProductDetails(ProductService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            request.setAttribute("product", service.getProductById(productId));
        } catch (NumberFormatException | DBException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return Pages.FORWARD_PRODUCT_DETAILS;
    }
}
