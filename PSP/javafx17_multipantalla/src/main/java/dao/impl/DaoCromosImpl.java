package dao.impl;

import common.config.Configuracion;
import dao.DaoCromos;
import dao.DaoGenerics;
import dao.retrofit.llamadas.JokeApi;
import dao.retrofit.modelo.ResponseJoke;
import domain.modelo.Cromo;
import domain.modelo.MiJokes;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.log4j.Log4j2;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Log4j2
public class DaoCromosImpl extends DaoGenerics implements DaoCromos {

    private final Configuracion configuracion;
    private final JokeApi jokeApi;


    @Inject
    public DaoCromosImpl(Configuracion configuracion, @Named("uno") JokeApi jokeApi) {
        this.configuracion = configuracion;
        this.jokeApi = jokeApi;

    }

    @Override
    public List<Cromo> loadCromos() {
        return List.of(
                new Cromo("marvel", 1, "descripcion"),
                new Cromo("marvel", 2, "descripcion"),
                new Cromo("marvel", 3, "descripcion"),
                new Cromo("marvel", 4, "descripcion"));
    }

    public Either<String, MiJokes> llamadaRettrofit() {


        Either<String, MiJokes> respuesta = null;

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("aslkjdsdf",e);


        }

        Response<ResponseJoke> r = null;
        try {
            r = jokeApi.getAnyJoke("aa", "es").execute();

            if (r.isSuccessful()) {
                ResponseJoke rj = r.body();
                MiJokes joker = null;
                if (rj.getType().equals("twoparts")) {
                    joker = new MiJokes(rj.getId(), rj.getSetup() + ":::" + rj.getDelivery());
                } else {
                    joker = new MiJokes(rj.getId(), rj.getJoke());
                }
                respuesta = Either.right(joker);
            } else {
                r.errorBody().string();

                respuesta = Either.left(r.message());
            }

        } catch (IOException e) {
            log.debug("he pasado por aqui");
            log.error(e.getMessage(), e);
            respuesta = Either.left(e.getMessage());
        }


        return respuesta;

    }

    public Single<Either<String, MiJokes>> llamadaRettrofitSingle() {


        return jokeApi.getAnyJokeAsync("es")
                .map(rj -> {
                    MiJokes joker = null;
                    if (rj.getType().equals("twoparts")) {
                        joker = new MiJokes(rj.getId(), rj.getSetup() + ":::" + rj.getDelivery());
                    } else {
                        joker = new MiJokes(rj.getId(), rj.getJoke());
                    }
                    return Either.right(joker).mapLeft(Object::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<String, MiJokes> error = Either.left("Error de comunicacion");
                    if (throwable instanceof HttpException httpException  ){
                            try (ResponseBody errorBody = Objects.requireNonNull(httpException.response()).errorBody()) {

                                if (Objects.equals(errorBody.contentType(), MediaType.get("application/json"))) {
//                                Gson g = new Gson();
//                                dao.modelo.marvel.ApiError apierror = g.fromJson(((HttpException) throwable).response().errorBody().string(), dao.modelo.marvel.ApiError.class);
//                                error = Either.left(apierror.getMessage());
                                } else {
                                    error = Either.left(httpException.response().message());
                                }
                            }
                        }

                    return error;
                });


    }

}
