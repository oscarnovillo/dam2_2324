package cliente.data.servicios;

import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

import java.util.List;

public interface EstupidoAPI {


    @GET("users")
    Single<List<Usuario>> getUsers();

    @GET("login")
    Single<Response<Void>> getLogin(@Header("Authorization") String basicAuthorization);

    @GET("alumno/filtro")
    Single<String> getJWT();

    @GET("alumno/verify")
    Single<String> getVerify();
}
