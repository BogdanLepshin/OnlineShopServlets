package ua.finalproject.controller.commands;

import ua.finalproject.controller.exceptions.DBException;
import ua.finalproject.controller.utils.ImageUploader;
import ua.finalproject.model.entity.Brand;
import ua.finalproject.model.entity.Category;
import ua.finalproject.model.entity.Product;
import ua.finalproject.model.service.BrandService;
import ua.finalproject.model.service.CategoryService;
import ua.finalproject.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditProduct implements Command {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    private final String FORWARD_EDIT_PRODUCT_PAGE = "/WEB-INF/pages/admin/edit_product.jsp";
    private final String REDIRECT_EDIT_PRODUCT_PAGE = "redirect:/products_manager/edit_product";
    private final String REDIRECT_PRODUCT_MANAGER_PAGE = "redirect:/products_manager";
    private final String UPLOAD_DIRECTORY = "product_images";
    private final Logger LOGGER = Logger.getLogger(NewProduct.class.getName());

    public EditProduct(ProductService productService, CategoryService categoryService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            try {
                request.setAttribute("product",
                        productService.getProductById(Integer.parseInt(request.getParameter("id"))));
                request.setAttribute("categories", categoryService.getAllCategories().orElse(new ArrayList<>()));
                request.setAttribute("brands", brandService.getAllBrands().orElse(new ArrayList<>()));
            } catch (NumberFormatException | DBException e) {
                e.printStackTrace();
                return REDIRECT_PRODUCT_MANAGER_PAGE;
            }
            return FORWARD_EDIT_PRODUCT_PAGE;
        }

        Category category = new Category();
        Brand brand = new Brand();
        try {
            String fileName = request.getPart("file").getSubmittedFileName();
            if (fileName.isEmpty()) {
                fileName = request.getParameter("product_image");
            }

            int id = Integer.parseInt(request.getParameter("id"));
            int brandId = Integer.parseInt(request.getParameter("brand"));
            int categoryId = Integer.parseInt(request.getParameter("category"));
            int price = Integer.parseInt(request.getParameter("price"));

            String name = request.getParameter("name");
            String descriptionRu = request.getParameter("description_ru");
            String descriptionEn = request.getParameter("description_en");

            brand.setId(brandId);
            category.setId(categoryId);
            Product product = productService.updateProduct(new Product.Builder()
                    .setId(id)
                    .setName(name)
                    .setBrand(brand)
                    .setCategory(category)
                    .setImage(fileName)
                    .setDescriptionRu(descriptionRu)
                    .setDescriptionEn(descriptionEn)
                    .setPrice(price)
                    .setEnabled(false)
                    .build());
            if (fileName != null) {
                String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + product.getId();
                ImageUploader.uploadImage(uploadPath, request);
            }
        } catch (NumberFormatException | ServletException | IOException | DBException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return REDIRECT_EDIT_PRODUCT_PAGE;
        }
        return REDIRECT_PRODUCT_MANAGER_PAGE;
    }
}
