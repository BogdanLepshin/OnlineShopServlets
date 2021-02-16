package ua.finalproject.controller.commands.admin;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.commands.Pages;
import ua.finalproject.controller.utils.ImageUploader;
import ua.finalproject.model.entity.Brand;
import ua.finalproject.model.entity.Category;
import ua.finalproject.model.entity.Product;
import ua.finalproject.model.service.BrandService;
import ua.finalproject.model.service.CategoryService;
import ua.finalproject.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewProduct implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final Logger LOGGER = Logger.getLogger(NewProduct.class.getName());


    private final String UPLOAD_DIRECTORY = "product_images";

    public NewProduct(ProductService productService, CategoryService categoryService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            request.setAttribute("categories", categoryService.getAllCategories().orElse(new ArrayList<>()));
            request.setAttribute("brands", brandService.getAllBrands().orElse(new ArrayList<>()));
            return Pages.FORWARD_NEW_PRODUCT_PAGE;
        }

        Category category = new Category();
        Brand brand = new Brand();
        int categoryId;
        int brandId;
        int price;
        try {
            brandId = Integer.parseInt(request.getParameter("brand"));
            brand.setId(brandId);
            categoryId = Integer.parseInt(request.getParameter("category"));
            category.setId(categoryId);
            price = Integer.parseInt(request.getParameter("price"));
        } catch (NumberFormatException e) {
            LOGGER.info("Invalid request params");
            return Pages.FORWARD_NEW_PRODUCT_PAGE;
        }

        try {
            String fileName = request.getPart("file").getSubmittedFileName();
            Product product = productService.saveProduct(new Product.Builder()
                    .setName(request.getParameter("name"))
                    .setBrand(brand)
                    .setCategory(category)
                    .setImage(fileName)
                    .setDescriptionRu(request.getParameter("description_ru"))
                    .setDescriptionEn(request.getParameter("description_en"))
                    .setPrice(price)
                    .setEnabled(false)
                    .build());
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + product.getId();
            ImageUploader.uploadImage(uploadPath, request);
        } catch (ServletException | IOException | SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return Pages.FORWARD_NEW_PRODUCT_PAGE;
        }
        return Pages.REDIRECT_PRODUCTS_MANAGER_PAGE;
    }

    /*private void uploadImage(HttpServletRequest request, int productId) throws IOException, ServletException {
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + productId;
        LOGGER.info(uploadPath);

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        for (Part part : request.getParts()) {
            String fileName = part.getSubmittedFileName();
            part.write(uploadPath + File.separator + fileName);
        }
    }*/
}
