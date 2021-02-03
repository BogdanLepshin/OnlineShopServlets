package ua.finalproject.controller.commands;

import ua.finalproject.model.entity.RoleType;
import ua.finalproject.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            HashSet<User> users = (HashSet<User>)request.getSession().getServletContext().getAttribute("loggedUsers");
            users.remove(user);
            request.getSession().invalidate();
        }
        /*CommandUtility.setUserRole(request, RoleType.ROLE_UNKNOWN, null);*/
        return "redirect:/login";
    }
}
