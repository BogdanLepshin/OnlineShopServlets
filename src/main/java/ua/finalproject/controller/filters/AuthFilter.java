package ua.finalproject.controller.filters;

import ua.finalproject.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        ServletContext context = session.getServletContext();

        User user = (User) req.getSession().getAttribute("user");

        if (req.getRequestURI().startsWith("/api")
                && isAccessRestricted(req) && req.getMethod().equals("GET")) {
            if (user == null) {
                req.getSession(false).setAttribute("access_error", true);
                res.sendRedirect("/api/login");
                return;
            }
        }

        if (isPageNoAuthAccess(req)) {
            if (user != null) {
                res.sendRedirect("/api/home");
                return;
            }
        }

        System.out.println(session);
        System.out.println(session.getAttribute("role"));
        System.out.println(context.getAttribute("loggedUsers"));

        filterChain.doFilter(request,response);
    }

    private boolean isPageNoAuthAccess(HttpServletRequest req) {
        return req.getRequestURI().equals("/api/login") || req.getRequestURI().equals("/api/register");
    }

    public boolean isAccessRestricted(HttpServletRequest request) {
        String uri = request.getRequestURI();

        return !uri.equals("/api/login") && !uri.equals("/api/register") && !uri.equals("/api/home");
    }

    @Override
    public void destroy() {

    }
}
