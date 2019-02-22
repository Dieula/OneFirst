package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("idCategories")
    @Expose
    private Integer idCategories;
    @SerializedName("NomCategories")
    @Expose
    private String NomCategories;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(Integer idCategories) {
        this.idCategories = idCategories;
    }

    public String getNomCategories() {
        return NomCategories;
    }

    public void setNomCategories(String nomCategories) {
        NomCategories = nomCategories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
