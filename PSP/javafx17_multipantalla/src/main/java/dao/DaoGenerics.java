package dao;

import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;

import java.util.Objects;

public abstract class DaoGenerics {

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


    public <T> Single<Either<String, T>> safeSingleApicall(Single<T> call) {
        return call.map(t -> Either.right(t).mapLeft(Object::toString))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<String, T> error = Either.left("Error de comunicacion");
                    if (throwable instanceof HttpException httpException) {
                        if (Objects.equals(httpException.response().errorBody().contentType(), MediaType.get("application/json"))) {
                            //Gson g = new Gson();
                            //ApiError apierror = g.fromJson(((HttpException) throwable).response().errorBody().string(), dao.modelo.marvel.ApiError.class);
                            //error = Either.left(apierror.getMessage());
                        } else {
                            error = Either.left(((HttpException) throwable).response().message());
                        }
                    }
                    return error;
                });


    }
}
