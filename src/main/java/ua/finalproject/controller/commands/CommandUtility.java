package ua.finalproject.controller.commands;

import ua.finalproject.model.entity.RoleType;
import ua.finalproject.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class CommandUtility {
    public static void setUserRole(HttpServletRequest request,
                            RoleType role, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", role);
    }

    public static boolean checkUserIsLogged(HttpServletRequest request, User user) {
        HashSet<User> loggedUsers = (HashSet<User>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");

        if (loggedUsers.stream().anyMatch((u)-> u.getEmail().equals(user.getEmail()))) {
            return true;
        }

        loggedUsers.add(user);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
