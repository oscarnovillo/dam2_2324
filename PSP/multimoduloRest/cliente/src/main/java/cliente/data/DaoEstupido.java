package cliente.data;

import com.google.gson.Gson;
import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import cliente.data.servicios.EstupidoAPI;
import jakarta.inject.Inject;

import java.util.List;

public class DaoEstupido extends DaoGenerics{


    private CacheAuthorization cache;
    private EstupidoAPI estupidoAPI;


    @Inject
    public DaoEstupido(EstupidoAPI estupidoAPI, Gson gson) {
        super(gson);
        this.estupidoAPI = estupidoAPI;
    }


    public Single<Either<String, List<Usuario>>> getUsuario(){


        return safeSingleApicall(estupidoAPI.getUsers())
                //.map(either -> either.map(alumno -> alumno.setNombre("mapeado")))
                .subscribeOn(Schedulers.io());
    }

    public Single<Either<String, String>> getLogin(String headerBasic ){
        return safeSingleVoidApicall(estupidoAPI.getLogin( headerBasic))
                //.map(either -> either.map(alumno -> alumno.setNombre("mapeado")))
                .subscribeOn(Schedulers.io());
    }


    public Single<Either<String,String>> getJwt(){
        return safeSingleApicall(estupidoAPI.getJWT())
                //.map(either -> either.map(alumno -> alumno.setNombre("mapeado")))
                .subscribeOn(Schedulers.io());

    }

    public Single<Either<String,String>> getVerify(){
        return safeSingleApicall(estupidoAPI.getVerify())
                //.map(either -> either.map(alumno -> alumno.setNombre("mapeado")))
                .subscribeOn(Schedulers.io());

    }


}
