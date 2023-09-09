package dao.retrofit.llamadas;

import dao.retrofit.modelo.ResponseJoke;
import dao.retrofit.modelo.ResponseJokeSimple;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface JokeApi {


    @GET("Any")
    Call<ResponseJoke> getAnyJoke(@Header("tkooen") String apiKey, @Query("lang") String lang);

    @GET("Any")
    Call<ResponseJokeSimple> getAnyJokeSimple(@Query("lang") String lang, @Query("type") String type);

    @GET("Programming")
    Call<ResponseJoke> getProgrammingJoke(@Query("lang") String lang);


    @GET("Any")
    Single<ResponseJoke> getAnyJokeAsync(@Query("lang") String lang);



}
