package miprimerRest.jakarta.filtros;


import domain.errores.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.time.LocalDateTime;

@Provider
@Secure
public class FiltroHorarioJAX implements ContainerRequestFilter {

    @Context
    private HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        jakarta.ws.rs.core.SecurityContext securityContext = containerRequestContext.getSecurityContext();

        if (securityContext.getUserPrincipal()!=null) {
            String name = securityContext.getUserPrincipal().getName();
        }

        if (request.getSession().getAttribute("LOGIN")==null)
        {
            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(ApiError.builder().message("error de filtro").fecha(LocalDateTime.now()).build())
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
    }
}
