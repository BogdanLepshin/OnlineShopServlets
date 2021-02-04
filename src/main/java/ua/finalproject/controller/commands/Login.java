package ua.finalproject.controller.commands;

import ua.finalproject.model.entity.RoleType;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    private final UserService userService;
    private final String FORWARD_LOGIN_PAGE = "/WEB-INF/pages/login.jsp";
    private final String REDIRECT_HOME = "redirect:/home";
    private final String AUTH_ERROR = "auth_error";
    private final String AUTH_ERROR_BAD_CREDENTIALS_MSG = "login.auth.badCredentials";
    private final String AUTH_ERROR_SESSION_EXISTS_MSG = "login.auth.sessionExists";

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return FORWARD_LOGIN_PAGE;
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = authenticate(email, password);

        if (isNotAuthenticated(user)) {
            request.setAttribute(AUTH_ERROR, AUTH_ERROR_BAD_CREDENTIALS_MSG);
            return FORWARD_LOGIN_PAGE;
        }

        if (CommandUtility.checkUserIsLogged(request, user)) {
            request.setAttribute(AUTH_ERROR, AUTH_ERROR_SESSION_EXISTS_MSG);
            return FORWARD_LOGIN_PAGE;
        }
        authorize(request, user);

        return REDIRECT_HOME;
    }

    private void authorize(HttpServletRequest request, User user) {
        if (user.getRole() == RoleType.ROLE_USER) {
            CommandUtility.setUserRole(request, RoleType.ROLE_USER, user);
            request.getSession().setAttribute("isUser", true);
        }
        if (user.getRole() == RoleType.ROLE_ADMIN) {
            CommandUtility.setUserRole(request, RoleType.ROLE_ADMIN, user);
            request.getSession().setAttribute("isAdmin", true);
        }
    }

    private User authenticate(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        return userService.findUserByEmailAndPassword(email, password);
    }

    private boolean isNotAuthenticated(User user) {
        return user == null;
    }

}
