package oneclick.yonclick.Model.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import oneclick.yonclick.Model.Model.Categorie;

public class CategorieList {

    @SerializedName("data")
    @Expose
    private List<Categorie> data = null;

    //private List<Categorie> produitby = null;


    public List<Categorie> getData() {
        return data;
    }

    public void setData(List<Categorie> data) {
        this.data = data;
    }

}
