package miprimerRest.jakarta.rest;



import domain.modelo.Usuario;
import dao.modelo.UsuarioEntity;
import io.vavr.control.Either;
import jakarta.annotation.security.RolesAllowed;
import domain.errores.ApiError;
import miprimerRest.jakarta.common.Constantes;
import miprimerRest.jakarta.filtros.RoleUser;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;
import domain.servicios.ServiciosUsuarios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path("/users")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestUsuarios {

    private ServiciosUsuarios su;

    private ModelMapper mapper;

//    @Context
//    private SecurityContext security;

    @Inject
    public RestUsuarios(ServiciosUsuarios su, ModelMapper mapper) {
        this.su = su;
        this.mapper = mapper;
//        this.security = security;
    }

    //para todos los metodos
    //@Context HttpServletRequest request;

    @GET
    @Path("/uno")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@QueryParam("id") String id,
                               @Context HttpServletRequest request) {

        AtomicReference<Response> r = new AtomicReference();
        su.dameUno(id)
                .peek(usuario -> r.set(Response.ok(usuario).build()))
                .peekLeft(apiError -> r.set(Response.status(Response.Status.NOT_FOUND)
                        .entity(apiError)
                        .build()));


        Response r1=null;
        Either<ApiError,Usuario> resultado =su.dameUno(id);
        if (resultado.isLeft())
        {
            r1 = Response.status(Response.Status.NOT_FOUND)
                    .entity(resultado.getLeft())
                    .build();
        }


        return r.get();

    }

    @GET
    @RoleUser
    @Path("/{id}")
    public Response getUnUsuario(@PathParam("id") String id,
                                @HeaderParam("kk") String head) {
        AtomicReference<Response> r = new AtomicReference();
        su.dameUno(id)
                .peek(usuario -> r.set(Response.ok().entity(usuario).build()))
                .peekLeft(apiError -> r.set(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiError(apiError.getMessage(), LocalDateTime.now()))
//
//                                ApiError.builder()
//                                .message("error not found")
//                                .fecha(LocalDateTime.now())
//                                .build())
                        .build()));

        return r.get();
    }

    @GET
    @RolesAllowed({Constantes.ROLE_ADMIN,"users"})
    public List<Usuario> getAllUsuario() {
        return su.dameTodos();
    }

    @GET
    @Path("/hib")
    public List<UsuarioEntity> getAllUsuarioHibernate() {
        return su.dameTodosHibernate();
    }


    @POST
    public Usuario addUsuario(Usuario user) {
        return su.addUser(user);
    }

    @DELETE
    public Response delUsuario(@QueryParam("id") String id) {
        if (su.borrar(id))
            return Response.status(Response.Status.NO_CONTENT).build();
        else
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiError.builder()
                            .message("usuario no encontrado")
                            .fecha(LocalDateTime.now())
                            .build())
                    .build();
    }
}
