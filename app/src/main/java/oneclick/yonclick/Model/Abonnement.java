package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Abonnement implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
