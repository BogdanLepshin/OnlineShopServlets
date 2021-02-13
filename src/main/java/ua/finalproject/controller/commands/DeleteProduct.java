package ua.finalproject.controller.commands;

import org.apache.commons.io.FileUtils;
import ua.finalproject.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteProduct implements Command {

    private final ProductService productService;
    private static final String DELETE_DIRECTORY = "product_images";
    private final String PRODUCTS_MANAGER_PAGE_PATH = "/WEB-INF/pages/admin/products_manager.jsp";
    private final String REDIRECT_PRODUCTS_MANAGER = "redirect:/api/products_manager";
    private final Logger LOGGER = Logger.getLogger(NewProduct.class.getName());

    public DeleteProduct(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            productService.deleteProduct(productId);
            deleteFolder(request, productId);
        } catch (NumberFormatException e) {
            LOGGER.severe("Invalid id");
            return REDIRECT_PRODUCTS_MANAGER;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Cannot delete folder", e.getMessage());
            return REDIRECT_PRODUCTS_MANAGER;
        }
        return REDIRECT_PRODUCTS_MANAGER;
    }

    private void deleteFolder(HttpServletRequest request, int productId) throws IOException {
        String deletePath = request.getServletContext().getRealPath("") + DELETE_DIRECTORY + File.separator + productId;
        File file = new File(deletePath);
        if (file.exists()) {
            FileUtils.deleteDirectory(file);
        }
    }
}
