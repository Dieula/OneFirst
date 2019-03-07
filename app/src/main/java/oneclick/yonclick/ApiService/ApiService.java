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

    @GET("product/get")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<ProduitList> getProduit();

    @GET("abonnement/get")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<AbonnementList> getAbonnementJSON();

    @GET("foodcategory/get")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<RestaurantList> getRestoJSON();

    @GET("food/get")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<PlatList> getPlatJSON();

    @GET("school/get")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<EcolageList> getEcolageJSON();


}
