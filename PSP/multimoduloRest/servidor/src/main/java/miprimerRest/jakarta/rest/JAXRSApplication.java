package miprimerRest.jakarta.rest;


import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import miprimerRest.jakarta.common.Constantes;

@ApplicationPath("/privado/api")
@DeclareRoles({Constantes.ROLE_ADMIN, "user"})
public class JAXRSApplication extends Application {


}
