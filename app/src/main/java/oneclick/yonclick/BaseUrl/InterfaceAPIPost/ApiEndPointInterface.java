package oneclick.yonclick.BaseUrl.InterfaceAPIPost;

import oneclick.yonclick.Model.Commande.RequestCommandes;
import oneclick.yonclick.Model.Commande.ResponseCommandes;
import oneclick.yonclick.Model.ModelAuth.RequestLogin;
import oneclick.yonclick.Model.ModelAuth.ResponseLogin;
import oneclick.yonclick.Model.ModelAuth.UserRequest;
import oneclick.yonclick.Model.ModelAuth.UserResponse;
import oneclick.yonclick.Model.NatcomModel.RequestComptepayem;
import oneclick.yonclick.Model.FormAbonnementEcolage.ResponseAbonnement;
import oneclick.yonclick.Model.NatcomModel.ResponseComptepayem;
import oneclick.yonclick.Model.FormAbonnementEcolage.RequestAbonnement;
import oneclick.yonclick.Model.FormAbonnementEcolage.RequestSchool;
import oneclick.yonclick.Model.FormAbonnementEcolage.ResponseSchool;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiEndPointInterface{

    @POST("user/create")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<UserResponse> utilisateur(@Body UserRequest userRequest);


    @POST("user/login")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<ResponseLogin> user(@Body RequestLogin requestLogin);


    @POST("comptepayem")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<ResponseComptepayem> Comptepayem (@Body RequestComptepayem requestComptepayem);

    @POST("commandes")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<ResponseCommandes> Commande (@Body RequestCommandes requestCommandes);


    @POST("payementplan")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<ResponseAbonnement> abonnment (@Body RequestAbonnement requestAbonnement);

    @POST("payementschool")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<ResponseSchool> school (@Body RequestSchool requestSchool);

}
