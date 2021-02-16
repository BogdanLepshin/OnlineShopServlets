package ua.finalproject.controller.commands.admin;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.commands.Pages;
import ua.finalproject.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductStatus implements Command {
    private final ProductService productService;
    private final Logger LOGGER = Logger.getLogger(ProductStatus.class.getName());

    public ProductStatus(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String status = Optional.ofNullable(request.getParameter("product_status")).orElse("");
            productService.updateStatus(id, status.equals("on"));
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return Pages.REDIRECT_PRODUCTS_MANAGER_PAGE;
    }
}
