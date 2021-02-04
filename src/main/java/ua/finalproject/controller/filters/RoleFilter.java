package ua.finalproject.controller.filters;

import ua.finalproject.model.entity.RoleType;
import ua.finalproject.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {
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
        User user = (User) session.getAttribute("user");

/*        if (user == null) {
            req.getSession(false).setAttribute("access_error", true);
            res.sendRedirect("/api/login");
            return;
        }*/
        if (user.getRole() == RoleType.ROLE_ADMIN) {
            filterChain.doFilter(request, response);
        }
        request.getRequestDispatcher("/WEB-INF/pages/accessDenied.jsp").forward(req, res);
    }

    @Override
    public void destroy() {

    }
}
