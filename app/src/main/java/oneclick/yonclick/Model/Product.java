package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("ID_Produits")
    @Expose
    private Integer ID_Produits;

    @SerializedName("Nom_Produits")
    @Expose
    private String Nom_Produits;

    @SerializedName("Details_Produits")
    @Expose
    private String Details_Produits;

    @SerializedName("prix")
    @Expose
    private String prix;
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("Id_Institution")
    @Expose
    private Integer Id_Institution;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Integer getID_Produits() {
        return ID_Produits;
    }

    public void setID_Produits(Integer ID_Produits) {
        this.ID_Produits = ID_Produits;
    }

    public String getNom_Produits() {
        return Nom_Produits;
    }

    public void setNom_Produits(String nom_Produits) {
        Nom_Produits = nom_Produits;
    }

    public String getDetails_Produits() {
        return Details_Produits;
    }

    public void setDetails_Produits(String details_Produits) {
        Details_Produits = details_Produits;
    }

    public Integer getId_Institution() {
        return Id_Institution;
    }

    public void setId_Institution(Integer id_Institution) {
        Id_Institution = id_Institution;
    }
}
