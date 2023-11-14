package miprimerRest.jakarta.filtros;


import domain.errores.ApiError;

import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.time.LocalDateTime;

@Provider
@RoleUser
public class FiltroRoleUser implements ContainerRequestFilter {


    @Context
    private HttpServletRequest request;


    private SecurityContext securityContext;




    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        securityContext = containerRequestContext.getSecurityContext();

        if (!securityContext.isUserInRole("user")) {
            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(ApiError.builder().message("error de filtro").fecha(LocalDateTime.now()).build())
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
    }
}