package oneclick.yonclick.BaseUrl.ApiServiceGetRequest;

import oneclick.yonclick.Model.ModelList.BrandList;
import oneclick.yonclick.Model.ModelList.AbonnementList;
import oneclick.yonclick.Model.ModelList.CategorieList;
import oneclick.yonclick.Model.ModelList.DegreList;
import oneclick.yonclick.Model.ModelList.EcolageList;
import oneclick.yonclick.Model.ModelList.MagasinsList;
import oneclick.yonclick.Model.ModelList.PlatList;
import oneclick.yonclick.Model.ModelList.ProduitList;
import oneclick.yonclick.Model.ModelList.RestaurantList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @GET("categoryproduct")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<CategorieList> getMyJSON();


    @GET("marque")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<BrandList> getMyBrandJSON();

    @GET("busness")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<MagasinsList> getMyStoreJSON();

    @GET("product")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<ProduitList> getProduit();

    @GET("abonnement")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<AbonnementList> getAbonnementJSON();

    @GET("restaurant")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<RestaurantList> getRestoJSON();

    @GET("food")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<PlatList> getPlatJSON();

    @GET("school")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<EcolageList> getEcolageJSON();

    @GET("degre")
    @Headers({"Content-Type: application/json","apiKey: 8484884774837498"})
    Call<DegreList> getDegreJSON();
}
