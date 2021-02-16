package ua.finalproject.controller.commands.admin;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.commands.Pages;
import ua.finalproject.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Users implements Command {

    private final UserService userService;

    public Users(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession(false) == null) {
            return Pages.REDIRECT_LOGIN_PAGE;
        }
        request.setAttribute("users", userService.getAllUsers());
        return Pages.FORWARD_USERS_PAGE;
    }
}
