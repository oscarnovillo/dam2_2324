package cliente.data.network;

import cliente.data.CacheAuthorization;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.HttpHeaders;

import java.io.IOException;

public class AuthorizationInterceptor implements Interceptor {


    private CacheAuthorization ca ;


    public AuthorizationInterceptor(CacheAuthorization ca) {
        this.ca = ca;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request ;

        if (ca.getJwt() == null) {
            request = original.newBuilder()
                    .header("Authorization", Credentials.basic(ca.getUser(), ca.getPass())).build();
        }
        else
        {
            request = original.newBuilder()
                    .header("JWT", "Bearer "+ca.getJwt()).build();

        }

        Response response = chain.proceed(request);
        if (response.header("Authorization") !=null)
            ca.setJwt(response.header("Authorization"));


        if (!response.isSuccessful())
        {
            //reintentar
            response.close();
            request = original.newBuilder()
                    .header("Authorization", Credentials.basic(ca.getUser(), ca.getPass())).build();
            response = chain.proceed(request);
            if (response.header("Authorization") !=null)
                ca.setJwt(response.header("Authorization"));
        }

        return response;
    }
}
