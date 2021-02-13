package ua.finalproject.controller;

import ua.finalproject.controller.commands.*;
import ua.finalproject.controller.commands.Exception;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@MultipartConfig
public class Servlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    private final BrandService brandService = new BrandService();
    private final CartService cartService = new CartService();
    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
            .setAttribute("loggedUsers", new HashSet<User>());

        commands.put("logout", new LogOut());
        commands.put("home", new Home());
        commands.put("login", new Login(userService));
        commands.put("users", new Users(userService));
        commands.put("register", new Registration(userService));
        commands.put("exception", new Exception());
        commands.put("products_manager", new ProductsManager(productService, categoryService));
        commands.put("products_manager/create_product", new NewProduct(productService, categoryService, brandService));
        commands.put("products_manager/create_product/save", new NewProduct(productService, categoryService, brandService));
        commands.put("products_manager/edit_product", new EditProduct(productService, categoryService, brandService));
        commands.put("products_manager/edit_product/save", new EditProduct(productService, categoryService, brandService));
        commands.put("products_manager/delete", new DeleteProduct(productService));
        commands.put("products_manager/product_status", new ProductStatus(productService));
        commands.put("products", new Products(productService));
        commands.put("products/product_details", new ProductDetails(productService));
        commands.put("products/addToCart", new AddToCart(productService, cartService));
        commands.put("cart", new CartPage(cartService));
        commands.put("cart/remove", new RemoveCartItem(cartService));
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println(path);
        path = path.replaceAll(".*/api/", "");
        System.out.println(path);
        Command command = commands.getOrDefault(path, new Home());
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/api"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
