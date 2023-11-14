package miprimerRest.jakarta.filtros;

import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Context;

import java.io.IOException;

@WebFilter(filterName = "Filter",urlPatterns = {"/privados/*"})
public class FilterRubenVeBien implements Filter {




    @Context
    private SecurityContext securityContext;

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {


        if (((HttpServletRequest)request).getSession().getAttribute("LOGIN")!=null)
            chain.doFilter(request, response);
        else
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN,"FORBIDEN");




    }
}
