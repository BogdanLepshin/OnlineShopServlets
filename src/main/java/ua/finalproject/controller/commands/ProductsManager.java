package ua.finalproject.controller.commands;

import ua.finalproject.model.service.CategoryService;
import ua.finalproject.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsManager implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final Logger LOGGER = Logger.getLogger(ProductsManager.class.getName());

    public ProductsManager(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("products", productService.getAllProducts().orElse(new ArrayList<>()));
        request.setAttribute("categories", categoryService.getAllCategories().orElse(new ArrayList<>()));

        if (request.getMethod().equals("GET")) {
            return Pages.FORWARD_PRODUCTS_MANAGER_PAGE;
        }

        int categoryId;
        try {
            categoryId = Integer.parseInt(request.getParameter("category"));
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return Pages.FORWARD_PRODUCTS_MANAGER_PAGE;
        }
        if (categoryId == 0) {
            return Pages.FORWARD_PRODUCTS_MANAGER_PAGE;
        }
        request.setAttribute("selected_category", categoryId);
        request.setAttribute("products",
                productService.getProductsByCategory(categoryId).orElse(new ArrayList<>()));

        return Pages.FORWARD_PRODUCTS_MANAGER_PAGE;
    }
}
