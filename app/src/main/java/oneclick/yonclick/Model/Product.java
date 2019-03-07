package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

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
