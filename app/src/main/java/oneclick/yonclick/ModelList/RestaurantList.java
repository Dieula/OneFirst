package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import oneclick.yonclick.Model.Employe;
import oneclick.yonclick.Model.Restaurant;

public class RestaurantList {
    @SerializedName("CatPlats")
    @Expose
    private ArrayList<Restaurant> restaurants = null;

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

}
