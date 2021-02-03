package ua.finalproject.controller.commands;

import ua.finalproject.model.entity.RoleType;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Login implements Command {
    private final UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return "/WEB-INF/pages/login.jsp";
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email + " " + password);

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("auth_error", true);
            return "/WEB-INF/pages/login.jsp";
        }

        User user = userService.findUserByEmailAndPassword(email, password);

        if (user == null) {
            request.setAttribute("auth_error", true);
            return "/WEB-INF/pages/login.jsp";
        }

        if(CommandUtility.checkUserIsLogged(request, user)){
            request.setAttribute("auth_error_session_exists", true);
            return "/WEB-INF/pages/login.jsp";
        }
        System.out.println(user);
        if (user.getRole() == RoleType.ROLE_USER) {
            CommandUtility.setUserRole(request, RoleType.ROLE_USER, user);
            request.setAttribute("isAdmin", false);

        }
        if (user.getRole() == RoleType.ROLE_ADMIN) {
            CommandUtility.setUserRole(request, RoleType.ROLE_ADMIN, user);
            request.setAttribute("isAdmin", true);
        }

        return "redirect:/users";
    }
}
