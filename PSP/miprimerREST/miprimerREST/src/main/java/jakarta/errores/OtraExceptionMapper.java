package jakarta.errores;

import domain.modelo.errores.OtraException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class OtraExceptionMapper implements ExceptionMapper<OtraException> {
    @Override
    public Response toResponse(OtraException e) {
        ApiError apiError = new ApiError(e.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.BAD_REQUEST).entity(apiError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
