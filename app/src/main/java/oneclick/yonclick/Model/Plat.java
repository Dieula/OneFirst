package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Plat implements Serializable {
    @SerializedName("ID_Plats")
    @Expose
    private Integer ID_Plats;

    @SerializedName("Nom_Plats")
    @Expose
    private String Nom_Plats;

    @SerializedName("Id_Restaurant")
    @Expose
    private Integer Id_Restaurant;

    @SerializedName("Details_Plats")
    @Expose
    private String Details_Plats;

    @SerializedName("prix")
    @Expose
    private String prix;

    @SerializedName("image")
    @Expose
    private String image;

    public Plat() {
    }

    public Plat(Integer ID_Plats, String nom_Plats, Integer id_Restaurant, String details_Plats, String prix, String image) {
        this.ID_Plats = ID_Plats;
        Nom_Plats = nom_Plats;
        Id_Restaurant = id_Restaurant;
        Details_Plats = details_Plats;
        this.prix = prix;
        this.image = image;
    }


    public Integer getID_Plats() {
        return ID_Plats;
    }

    public void setID_Plats(Integer ID_Plats) {
        this.ID_Plats = ID_Plats;
    }

    public String getNom_Plats() {
        return Nom_Plats;
    }

    public void setNom_Plats(String nom_Plats) {
        Nom_Plats = nom_Plats;
    }

    public Integer getId_Restaurant() {
        return Id_Restaurant;
    }

    public void setId_Restaurant(Integer id_Restaurant) {
        Id_Restaurant = id_Restaurant;
    }

    public String getDetails_Plats() {
        return Details_Plats;
    }

    public void setDetails_Plats(String details_Plats) {
        Details_Plats = details_Plats;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
