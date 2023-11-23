package miprimerRest.jakarta.filtros;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Context;

import java.io.IOException;

@WebFilter(filterName = "FilterRoleUser",urlPatterns = {"/api/*"})
public class FilterRoleUser implements Filter {

    private final SecurityContext securityContext;


    @Inject
    public FilterRoleUser(@Context SecurityContext securityContext) {
        this.securityContext = securityContext;
    }




    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (securityContext.isCallerInRole("user") && ((HttpServletRequest)request).getMethod().equals("GET"))
            chain.doFilter(request, response);
        else
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN,"FORBIDEN");

    }
}
