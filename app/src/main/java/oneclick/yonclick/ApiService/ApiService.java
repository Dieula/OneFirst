package oneclick.yonclick.ApiService;

import oneclick.yonclick.ModelList.AbonnementList;
import oneclick.yonclick.ModelList.CategorieList;
import oneclick.yonclick.ModelList.ProduitList;
import oneclick.yonclick.ModelList.RestaurantList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    /*
   Retrofit get annotation with our URL
   And our method that will return us the List of EmployeeList
   */
    @GET("services/brand.php")
    Call<CategorieList> getMyJSON();

    @GET("services/listeproduct.php")
    Call<ProduitList> getProduit();

    @GET("services/abonnement.php")
    Call<AbonnementList> getAbonnementJSON();

    @GET("retrofit/json_object.json")
    Call<RestaurantList> getRestoJSON();
}
