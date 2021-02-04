package ua.finalproject.controller;

import ua.finalproject.controller.commands.*;
import ua.finalproject.controller.commands.Exception;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {

    UserService userService = new UserService();
    Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
            .setAttribute("loggedUsers", new HashSet<User>());

        commands.put("logout", new LogOut());
        commands.put("home", new Home());
        commands.put("login", new Login(userService));
        commands.put("users", new Users(userService));
        commands.put("register", new Registration(userService));
        commands.put("exception", new Exception());
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
