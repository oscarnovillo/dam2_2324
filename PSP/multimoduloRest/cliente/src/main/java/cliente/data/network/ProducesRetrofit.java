package cliente.data.network;


import cliente.data.servicios.EstupidoAPI;
import com.google.gson.*;
import com.squareup.moshi.Moshi;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class ProducesRetrofit {


    @Produces
    @Singleton
    public Gson getGson()
    {
        return  new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.toString()))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, type, jsonSerializationContext) -> new JsonPrimitive(localDate.toString())
                ).create();
    }


    @Produces
    @Singleton
    public OkHttpClient getOkHttpClient()
    {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        return  new OkHttpClient.Builder()
                .readTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .callTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .connectTimeout(Duration.of(10, ChronoUnit.MINUTES))
                //.addInterceptor(new AuthorizationInterceptor(cache))

                .connectionPool(new ConnectionPool(1, 1, TimeUnit.SECONDS))
                // necesario para la sesion
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();
    }



    @Produces
    @Singleton
    public Retrofit retrofits(Gson gson, OkHttpClient clientOK ) {

        return  new Retrofit.Builder()
                .baseUrl("http://localhost:8080/miprimerREST-1.0-SNAPSHOT/privado/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientOK)
                .build();
    }


    @Produces
    public EstupidoAPI getEstupidoApi(Retrofit retrofit){
        return retrofit.create(EstupidoAPI.class);
    }



}
