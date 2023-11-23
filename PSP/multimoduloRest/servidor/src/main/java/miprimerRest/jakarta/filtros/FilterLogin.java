package miprimerRest.jakarta.filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter(filterName = "FilterRoleUser",urlPatterns = {"/privado/api/*"})
public class FilterLogin  implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // filter login
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("LOGIN") == null) {
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN,"FORBIDEN");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
