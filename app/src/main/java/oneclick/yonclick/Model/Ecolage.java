package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ecolage {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom_etablissements")
    @Expose
    private String nom_etablissements;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom_etablissements() {
        return nom_etablissements;
    }

    public void setNom_etablissements(String nom_etablissements) {
        this.nom_etablissements = nom_etablissements;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
