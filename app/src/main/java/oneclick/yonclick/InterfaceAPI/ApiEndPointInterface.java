package oneclick.yonclick.InterfaceAPI;

import oneclick.yonclick.ModelAuth.RequestLogin;
import oneclick.yonclick.ModelAuth.ResponseLogin;
import oneclick.yonclick.ModelAuth.UserRequest;
import oneclick.yonclick.ModelAuth.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiEndPointInterface{
    @POST("user/create")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<UserResponse> utilisateur(@Body UserRequest userRequest);


    @POST("user/login ")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<ResponseLogin> user(@Body RequestLogin requestLogin);


}
