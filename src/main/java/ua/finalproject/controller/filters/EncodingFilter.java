package ua.finalproject.controller.filters;


import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest.getCharacterEncoding() == null) {
            servletResponse.setCharacterEncoding("UTF-8");
            servletRequest.setCharacterEncoding("UTF-8");
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
