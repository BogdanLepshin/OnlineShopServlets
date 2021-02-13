package ua.finalproject.controller.commands;

import ua.finalproject.controller.validation.RegistrationFormValidator;
import ua.finalproject.controller.validation.Validator;
import ua.finalproject.model.entity.RoleType;
import ua.finalproject.model.entity.User;
import ua.finalproject.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registration implements Command {
    private final Logger LOGGER = Logger.getLogger(Registration.class.getName());
    private final UserService userService;
    private final Validator<User> validator = new RegistrationFormValidator();

    private final String REGISTER_PAGE_PATH = "/WEB-INF/pages/register.jsp";
    private final String REDIRECT_LOGIN = "redirect:/login";

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return REGISTER_PAGE_PATH;
        }

        User user = User.builder()
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setEmail(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .setRole(RoleType.ROLE_USER)
                .build();

        validator.validate(user);

        if (validator.hasErrors()) {
            user = validator.clearInvalidFields(user);
            addErrorsMessagesToRequest(request);
            request.setAttribute("user", user);

            return REGISTER_PAGE_PATH;
        }

        try {
            userService.save(user);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            request.setAttribute("userExists", true);
            request.setAttribute("user", user);

            return REGISTER_PAGE_PATH;
        }

        return REDIRECT_LOGIN;
    }

    private void addErrorsMessagesToRequest(HttpServletRequest request) {
        for (Map.Entry<String, Boolean> error : validator.getErrors().entrySet()) {
            request.setAttribute(error.getKey(), error.getValue());
        }
    }


}
