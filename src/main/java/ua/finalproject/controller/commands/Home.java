package ua.finalproject.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class Home implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/pages/home.jsp";
    }
}
