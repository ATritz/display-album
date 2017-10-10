package tritza.com.albums.display;

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    String SERVICE_ENDPOINT = "http://jsonplaceholder.typicode.com";

    /**
     * Create the okhhtp client for retrofit
     * @return
     */
    private OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request original = chain.request();
                final HttpUrl originalHttpUrl = original.url();
                final HttpUrl url = originalHttpUrl.newBuilder()
                    .build();
                final Request.Builder requestBuilder = original.newBuilder()
                    .url(url);
                final Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    /**
     * Create the retrofit service to make api calls
     * @return
     */
    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
            .baseUrl(SERVICE_ENDPOINT)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }

    /**
     * Create the retrofit service with the Album Api
     * @return
     */
    public AlbumApi getAlbumApi() {
        final Retrofit retrofit = createRetrofit();
        return retrofit.create(AlbumApi.class);
    }
}
