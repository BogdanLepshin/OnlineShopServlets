package ua.finalproject.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class Exception implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        throw new RuntimeException("Generated exception");
    }
}
