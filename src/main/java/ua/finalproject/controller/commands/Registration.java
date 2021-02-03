package ua.finalproject.controller.commands;

import ua.finalproject.controller.validation.FormValidator;
import ua.finalproject.model.entity.RoleType;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public class Registration implements Command {

    private final UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return "/WEB-INF/pages/register.jsp";
        }
        FormValidator validator = new FormValidator();

        User user = User.builder()
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setEmail(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .setRole(RoleType.ROLE_USER)
                .build();

        user = validator.validateRegistrationForm(user);

        if (validator.hasErrors()) {
            for (Map.Entry<String, Boolean> error : validator.getErrors().entrySet()) {
                request.setAttribute(error.getKey(), error.getValue());
            }
            request.setAttribute("user", user);
            return "/WEB-INF/pages/register.jsp";
        }

        try {
            userService.save(user);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("userExistsAlready", true);
            request.setAttribute("user", user);
            return "/WEB-INF/pages/register.jsp";
        }

        return "redirect:/login";
    }
}
