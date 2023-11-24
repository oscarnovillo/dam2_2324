package cliente.data;

import cliente.domain.errores.ErrorApp;
import com.google.gson.Gson;
import domain.errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;

import java.util.Objects;

abstract class DaoGenerics {


    private Gson gson;

    @Inject
    protected DaoGenerics(Gson gson) {
        this.gson = gson;
    }

    public <T> Either<String, T> safeApicall(Call<T> call) {
        Either<String, T> resultado = null;

        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                resultado = Either.right(response.body());
            } else {

                resultado = Either.left(response.errorBody().toString());
            }
        } catch (Exception e) {
            resultado = Either.left("Error de comunicacion");

        }

        return resultado;
    }


    public <T> Single<Either<ErrorApp, T>> safeSingleApicall(Single<T> call) {
        return call.map(t ->
                    Either.right(t).mapLeft(o -> (ErrorApp)o)
                )
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<ErrorApp, T> error = Either.left(new ErrorApp("Error de comunicacion"));

                    if (throwable instanceof HttpException) {
                        int code = ((HttpException) throwable).code();
                        if (((HttpException) throwable).response().errorBody()!= null) {
                            if (Objects.equals(((HttpException) throwable).response().errorBody().contentType(), MediaType.get("application/json"))) {
                                ApiError api = gson.fromJson(((HttpException) throwable).response().errorBody().charStream(), ApiError.class);
                                error = Either.left(new ErrorApp(code + api.getMessage()));


                                //error = Either.right(T);
                            } else {
                                error = Either.left(new ErrorApp(((HttpException) throwable).response().message()));
                            }
                        }
                    }
                    return error;
                });


    }

    public Single<Either<ErrorApp, String>> safeSingleVoidApicall(Single<Response<Void>> call) {
        return call.map(response -> {
                    Either<ErrorApp,String> retorno = Either.right("OK");
                    if (!response.isSuccessful()) {
                        retorno = Either.left(new ErrorApp(response.message()));
                    }
                    return retorno;
                })
                .subscribeOn(Schedulers.io());
    }

}
