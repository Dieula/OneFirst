package oneclick.yonclick.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Plats implements Serializable{

    public String id;
    public String name_plat;
    public String description_plat;
    public String prix_plat;
    public String imgPlat;


    public Plats() {
    }

    public Plats(String id, String name_plat, String description_plat, String prix_plat, String imgPlat) {
        this.id = id;
        this.name_plat = name_plat;
        this.description_plat = description_plat;
        this.prix_plat = prix_plat;
        this.imgPlat = imgPlat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_plat() {
        return name_plat;
    }

    public void setName_plat(String name_plat) {
        this.name_plat = name_plat;
    }

    public String getDescription_plat() {
        return description_plat;
    }

    public void setDescription_plat(String description_plat) {
        this.description_plat = description_plat;
    }

    public String getPrix_plat() {
        return prix_plat;
    }

    public void setPrix_plat(String prix_plat) {
        this.prix_plat = prix_plat;
    }

    public String getImgPlat() {
        return imgPlat;
    }

    public void setImgPlat(String imgPlat) {
        this.imgPlat = imgPlat;
    }

    public Plats(JSONObject jsonObject) throws JSONException {

        this.id = jsonObject.getString("ID_Plats");
        this.name_plat = jsonObject.getString("Nom_Plats");
        this.description_plat = jsonObject.getString("Details_Plats");
        this.prix_plat = jsonObject.getString("prix");
        this.imgPlat = jsonObject.getString("image");


    }

    public static ArrayList<Plats> fromJSONArray(JSONArray array) {

        ArrayList<Plats> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Plats(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
