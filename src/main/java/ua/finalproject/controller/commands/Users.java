package ua.finalproject.controller.commands;

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
            return "redirect:/login";
        }
        request.setAttribute("users", userService.getAllUsers());
        return "/WEB-INF/pages/users.jsp";
    }
}
