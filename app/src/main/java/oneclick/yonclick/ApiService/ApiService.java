package oneclick.yonclick.ApiService;

import oneclick.yonclick.ModelList.AbonnementList;
import oneclick.yonclick.ModelList.CategorieList;
import oneclick.yonclick.ModelList.EcolageList;
import oneclick.yonclick.ModelList.PlatList;
import oneclick.yonclick.ModelList.ProduitList;
import oneclick.yonclick.ModelList.RestaurantList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @GET("categoryproduct/get")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<CategorieList> getMyJSON();

    @GET("services/listeproduct.php")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<ProduitList> getProduit();

    @GET("services/abonnement.php")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<AbonnementList> getAbonnementJSON();

    @GET("foodcategory/get")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<RestaurantList> getRestoJSON();

    @GET("services/listeplats.php")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<PlatList> getPlatJSON();

    @GET("services/liste_all_ecoles.php")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498","accessToken: 64e72de411826abbc5557fb1b71d451b"})
    Call<EcolageList> getEcolageJSON();


}
