package oneclick.yonclick.Model.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name_product")
    @Expose
    private String name_product;

    @SerializedName("details_product")
    @Expose
    private String details_product;

    @SerializedName("prix")
    @Expose
    private String prix;
    @SerializedName("image")
    @Expose
    private String image;


    @SerializedName("Id_Institution")
    @Expose
    private Integer Id_Institution;


    @SerializedName("brand_id")
    @Expose
    private Integer brand_id;


    @SerializedName("departements_id")
    @Expose
    private Integer departements_id;


    @SerializedName("created_at")
    @Expose
    private String created_at;


    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("quantite_en_stock")
    @Expose
    private Integer quantite_en_stock;

    @SerializedName("posted_by")
    @Expose
    private Integer posted_by;


    @SerializedName("en_vedette")
    @Expose
    private String en_vedette;


    @SerializedName("nouveaute")
    @Expose
    private String nouveaute;


    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getDepartements_id() {
        return departements_id;
    }

    public void setDepartements_id(Integer departements_id) {
        this.departements_id = departements_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getQuantite_en_stock() {
        return quantite_en_stock;
    }

    public void setQuantite_en_stock(Integer quantite_en_stock) {
        this.quantite_en_stock = quantite_en_stock;
    }

    public Integer getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(Integer posted_by) {
        this.posted_by = posted_by;
    }

    public String getEn_vedette() {
        return en_vedette;
    }

    public void setEn_vedette(String en_vedette) {
        this.en_vedette = en_vedette;
    }

    public String getNouveaute() {
        return nouveaute;
    }

    public void setNouveaute(String nouveaute) {
        this.nouveaute = nouveaute;
    }

    public Integer getId() {
        return id;
    }

    public String getName_product() {
        return name_product;
    }

    public String getDetails_product() {
        return details_product;
    }

    public String getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public Integer getId_Institution() {
        return Id_Institution;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public void setDetails_product(String details_product) {
        this.details_product = details_product;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId_Institution(Integer id_Institution) {
        Id_Institution = id_Institution;
    }
}
