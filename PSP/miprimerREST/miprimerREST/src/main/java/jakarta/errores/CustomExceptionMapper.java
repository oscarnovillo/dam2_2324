package jakarta.errores;

import domain.modelo.errores.CustomException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;


@Provider
public class CustomExceptionMapper implements ExceptionMapper<CustomException> {

  public Response toResponse(CustomException exception) {
    ApiError apiError = new ApiError(exception.getMessage(), LocalDateTime.now());
    return Response.status(exception.getCodigo()).entity(apiError)
        .type(MediaType.APPLICATION_JSON_TYPE).build();
  }

}
