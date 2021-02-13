package ua.finalproject.controller.commands;

import ua.finalproject.model.entity.Product;
import ua.finalproject.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Products implements Command {
    private final ProductService productService;
    private final Logger LOGGER = Logger.getLogger(Products.class.getName());

    public Products(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        try {
            int currentPage = Integer.parseInt(Optional.ofNullable(request.getParameter("currentPage")).orElse("1"));
            int recordsPerPage = 9;
            int category = Integer.parseInt(request.getParameter("category"));
            List<Product> products = productService.getProductsPageable(currentPage, recordsPerPage, category).orElse(new ArrayList<>());
            request.setAttribute("products", products);
            int rows = products.size();
            int numberOfPages = rows / recordsPerPage;

            if (numberOfPages % recordsPerPage > 0) {
                numberOfPages++;
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return Pages.FORWARD_PRODUCTS_PAGE;
        }


        return Pages.FORWARD_PRODUCTS_PAGE;
    }
}
