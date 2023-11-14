package miprimerRest.jakarta.security;

import domain.servicios.ServiciosUsuarios;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.RememberMeCredential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;


public class InMemoryIdentityStore implements IdentityStore {

    private final ServiciosUsuarios serviciosUsuarios;

    @Override
    public int priority() {
        return 10;
    }


    @Inject
    public InMemoryIdentityStore(ServiciosUsuarios serviciosUsuarios) {
        this.serviciosUsuarios = serviciosUsuarios;
    }


    @Override
    public CredentialValidationResult validate(Credential credential) {


        if (credential instanceof BasicAuthenticationCredential user) {

                HashSet<String> roles = new HashSet<>();
                roles.add("admin");
                roles.add("user");

                user.getPassword().getValue();
                return switch (user.getCaller()) {
                    case "admin" -> new CredentialValidationResult("admin", Set.of("admin"));// Collections.singleton("ADMIN"));
                    case "paco" -> new CredentialValidationResult("paco", Collections.singleton("user"));
                    case "user" -> new CredentialValidationResult("user", Collections.singleton("user"));
                    case "usuarioSinActivar" -> NOT_VALIDATED_RESULT;
                    default -> INVALID_RESULT;
                };

            }
        else if (credential instanceof RememberMeCredential jwt) {
                jwt.getToken();

            }
        else { throw new IllegalStateException("Unexpected value: " + credential);
        }


        return INVALID_RESULT;

    }

}
