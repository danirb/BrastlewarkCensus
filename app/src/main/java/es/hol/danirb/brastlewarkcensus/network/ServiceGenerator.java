package es.hol.danirb.brastlewarkcensus.network;


import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by dani on 17/11/15.
 */
class ServiceGenerator {
    private static final String API_BASE_URL = "http://alt17android.hol.es";
    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
