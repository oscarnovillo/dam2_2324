package dao.retrofit;


import com.squareup.moshi.Moshi;
import common.config.Configuracion;
import dao.retrofit.llamadas.JokeApi;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.time.LocalDateTime;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi()
    {

        return new Moshi.Builder().build();
    }



    @Produces
    public OkHttpClient getOK(Configuracion config)
    {
        return new OkHttpClient.Builder()

                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                //.protocols(java.util.Arrays.asList(Protocol.HTTP_2,Protocol.H2_PRIOR_KNOWLEDGE))
                .addInterceptor(chain -> {
                    Request original = chain.request();



                    Request.Builder builder1 = original.newBuilder()
                            .header("X-Auth-Token", "2deee83e549c4a6e9709871d0fd58a0a")
                            .url(original.url().newBuilder()
                                    .addQueryParameter("ts","1")
                                    .addQueryParameter("apikey","a26d34b6ea64ce618360835be5888f91")
                                    .addQueryParameter("hash","073e520a55d710ef1b77df866349e689")
                                    .build());

                    Request request = builder1.build();
                    original.newBuilder().header("Authorization", config.getPathDatos()).build();
                    return chain.proceed(request);}
                )
                .build();

    }

    @Produces
    @Cantante
    @Singleton
    public Retrofit retrofits(OkHttpClient clientOK,Moshi moshi, Configuracion config ) {
        return new Retrofit.Builder()
                .baseUrl("https://v2.jokeapi.dev/joke/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                //.addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientOK)
                .build();
    }


    @Produces
    @Named("Pelis")
    @Singleton
    public Retrofit retrofit(OkHttpClient clientOK,Moshi moshi, Configuracion config ) {
        return new Retrofit.Builder()
                .baseUrl("marvel")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                //.addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientOK)

                .build();
    }

    @Produces
    @Named("uno")
    public JokeApi getJokeApi(@Cantante Retrofit retrofit){
        return retrofit.create(JokeApi.class);
    }


    @Produces
    @Named("dos")
    public JokeApi getPelisApi(@Named("Pelis") Retrofit retrofit){
        return retrofit.create(JokeApi.class);
    }




}
