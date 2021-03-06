package oneclick.yonclick.BaseUrl.InterfaceAPIPost;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    public static final String BASE_URL="http://45.76.247.112/api/";
    public static final String BASE_URL_Image="http://45.76.247.112/";
    private static Retrofit retrofit = null;
    private static ApiEndPointInterface apiService;


    public static ApiEndPointInterface getApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(65, TimeUnit.SECONDS)
                .build();


        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(ApiEndPointInterface.class);
        }
        return  apiService;
    }
}
