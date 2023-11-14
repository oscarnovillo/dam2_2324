package cliente.data;

import jakarta.inject.Singleton;
import lombok.Data;



@Data
@Singleton
public class CacheAuthorization {

    private String user;
    private String pass;
    private String jwt;
}
