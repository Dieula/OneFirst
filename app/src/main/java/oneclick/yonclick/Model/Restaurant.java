package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class Restaurant implements Serializable
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_busness")
    @Expose
    private String name_busness;

    @SerializedName("image_compagnie")
    @Expose
    private String image_compagnie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_busness() {
        return name_busness;
    }

    public void setName_busness(String name_busness) {
        this.name_busness = name_busness;
    }

    public String getImage_compagnie() {
        return image_compagnie;
    }

    public void setImage_compagnie(String image_compagnie) {
        this.image_compagnie = image_compagnie;
    }
}


/*

public class Restaurant implements Serializable {

    String name_categorie ;
    String image;
    String id;
    String accessToken;


    public Restaurant(String name_categorie, String image, String id, String accessToken) {
        this.name_categorie = name_categorie;
        this.image = image;
        this.id = id;
        this.accessToken = accessToken;
    }


    public Restaurant() {
    }

    public String getName_categorie() {
        return name_categorie;
    }

    public void setName_categorie(String name_categorie) {
        this.name_categorie = name_categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Restaurant(JSONObject jsonObject) throws JSONException {

        this.id = jsonObject.getString("id");
        this.name_categorie = jsonObject.getString("name_categorie");
        this.image = jsonObject.getString("image");
    }

    public static ArrayList<Restaurant> fromJSONArray(JSONArray array) {

        ArrayList<Restaurant> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Restaurant(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
*/


    /*  @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_categorie")
    @Expose
    private String name_categorie;

    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_categorie() {
        return name_categorie;
    }

    public void setName_categorie(String name_categorie) {
        this.name_categorie = name_categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }*/

