package oneclick.yonclick.Model.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import oneclick.yonclick.Model.Model.Restaurant;

public class RestaurantList {

    @SerializedName("data")
    @Expose
    private List<Restaurant> restaurants = null;

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

}
