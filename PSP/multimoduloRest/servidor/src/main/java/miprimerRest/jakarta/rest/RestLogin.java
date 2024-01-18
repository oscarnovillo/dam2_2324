package miprimerRest.jakarta.rest;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.io.IOException;

@Path("/login")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {



    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getLogin() {
        try {
            request.authenticate(response);
            securityContext.isUserInRole("admin");
            request.getSession().setAttribute("LOGIN", true);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }

    @GET
    @Path("/activation")
    @Produces(MediaType.TEXT_HTML)
    public Response getActivation(@QueryParam("user") String user, @QueryParam ("password") String password) {
        return Response.ok("<html><body><h1>Activado</h1></body></html>").build();
    }

    @GET
    @Path("/login")
    public Response getLoginGet(@QueryParam("user") String user, @QueryParam ("password") String password) {
            request.getSession().setAttribute("LOGIN", null);
            if (user == null || password == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            if (!user.equals("admin") || !password.equals("admin"))
                return Response.status(Response.Status.UNAUTHORIZED).build();

            request.getSession().setAttribute("LOGIN", true);
            return Response.status(Response.Status.NO_CONTENT).build();
    }

}
