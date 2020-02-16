import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class RetrofitTest {
    private static HttpLoggingInterceptor logging
            = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

    public static void main(String[] args) throws IOException {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().followRedirects(true)
                .addInterceptor(new BasicAuthInterceptor("admin", "admin"))
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addNetworkInterceptor(new BasicAuthInterceptor("admin", "admin"))
                .addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8081")// change ur ip
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();

        Call call = retrofit.create(UserService.class).getUsers();
        Response response = call.execute();
        System.out.println(response.body());
    }
}
