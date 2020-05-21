package server;

import entity.CurrentState;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isHTTP(servletRequest, servletResponse) && isRequestValid((HttpServletRequest) servletRequest))
            filterChain.doFilter(servletRequest, servletResponse);
        if (!isRequestValid((HttpServletRequest) servletRequest)) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect("/login");
        }
    }

    private boolean isRequestValid(HttpServletRequest servletRequest) {
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies == null) return false;
        if(CurrentState.getCurrentUser() == null) return false;
        for (Cookie c : cookies) {
            if (c.getName().equals(CurrentState.getCurrentUser().getName()) && c.getValue().equals(CurrentState.getCurrentUser().getPassword())) {
                return true;
            }
        }
        return false;
    }

    private boolean isHTTP(ServletRequest servletRequest, ServletResponse servletResponse) {
        return servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse;
    }


    @Override
    public void destroy() {

    }
}
