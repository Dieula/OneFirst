package oneclick.yonclick.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

    public String id;
    public String name_departements;
    public String posted_by;
    public String created_at;
    public String image;
    public List<GetCategoryWithProduit> getCategoryWithProduits;

    public Category(String id, String name_departements, String posted_by,
                    String created_at, String image, List<GetCategoryWithProduit> getCategoryWithProduits) {
        this.id = id;
        this.name_departements = name_departements;
        this.posted_by = posted_by;
        this.created_at = created_at;
        this.image = image;
        this.getCategoryWithProduits = getCategoryWithProduits;
    }


    public Category(JSONObject jsonObject) throws JSONException {

        this.id = jsonObject.getString("id");
        this.name_departements = jsonObject.getString("name_departements");
        this.posted_by = jsonObject.getString("posted_by");
        this.created_at = jsonObject.getString("created_at");
        this.image = jsonObject.getString("image");


    }

    public static ArrayList<Category> fromJSONArray(JSONArray array) {

        ArrayList<Category> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Category(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_departements() {
        return name_departements;
    }

    public void setName_departements(String name_departements) {
        this.name_departements = name_departements;
    }

    public String getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(String posted_by) {
        this.posted_by = posted_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<GetCategoryWithProduit> getGetCategoryWithProduits() {
        return getCategoryWithProduits;
    }

    public void setGetCategoryWithProduits(List<GetCategoryWithProduit> getCategoryWithProduits) {
        this.getCategoryWithProduits = getCategoryWithProduits;
    }
}
