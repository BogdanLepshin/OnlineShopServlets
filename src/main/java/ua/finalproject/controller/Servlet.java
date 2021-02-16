package ua.finalproject.controller;

import ua.finalproject.controller.commands.*;
import ua.finalproject.controller.commands.Exception;
import ua.finalproject.controller.commands.admin.*;
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
    private final OrderService orderService = new OrderService();

    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
            .setAttribute("loggedUsers", new HashSet<User>());

        commands.put("admin/users", new Users(userService));
        commands.put("admin/products_manager", new ProductsManager(productService, categoryService));
        commands.put("admin/products_manager/create_product", new NewProduct(productService, categoryService, brandService));
        commands.put("admin/products_manager/create_product/save", new NewProduct(productService, categoryService, brandService));
        commands.put("admin/products_manager/edit_product", new EditProduct(productService, categoryService, brandService));
        commands.put("admin/products_manager/edit_product/save", new EditProduct(productService, categoryService, brandService));
        commands.put("admin/products_manager/delete", new DeleteProduct(productService));
        commands.put("admin/products_manager/product_status", new ProductStatus(productService));
        commands.put("admin/orders_manager", new OrdersManager(orderService));
        commands.put("admin/orders_manager/order_details", new OrderDetailsAdmin(orderService));
        commands.put("admin/orders_manager/order_details/change_status", new ChangeOrderStatus(orderService));

        commands.put("logout", new LogOut());
        commands.put("home", new Home());
        commands.put("login", new Login(userService));
        commands.put("register", new Registration(userService));
        commands.put("exception", new Exception());
        commands.put("products", new Products(productService));
        commands.put("products/product_details", new ProductDetails(productService));
        commands.put("products/addToCart", new AddToCart(productService, cartService));
        commands.put("cart", new CartPage(cartService));
        commands.put("cart/remove", new RemoveCartItem(cartService));
        commands.put("make_order", new MakeOrder(orderService, cartService, userService));
        commands.put("orders", new Orders(orderService));
        commands.put("orders/order_details", new OrderDetailsPage(orderService));
        commands.put("orders/order_details/cancel", new CancelOrder(orderService));
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
