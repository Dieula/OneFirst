package oneclick.yonclick.InterfaceAPI;

import oneclick.yonclick.ModelAuth.RequestLogin;
import oneclick.yonclick.ModelAuth.ResponseLogin;
import oneclick.yonclick.ModelAuth.UserRequest;
import oneclick.yonclick.ModelAuth.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiEndPointInterface{
    @POST("user/create")
    Call<UserResponse> utilisateur (@Body UserRequest userRequest);

    @POST("user/login ")
    Call<ResponseLogin> user (@Body RequestLogin requestLogin);

}
