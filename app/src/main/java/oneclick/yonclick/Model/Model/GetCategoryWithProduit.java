package oneclick.yonclick.Model.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetCategoryWithProduit implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_product")
    @Expose
    private String nameProduct;
    @SerializedName("details_product")
    @Expose
    private String detailsProduct;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("brand_id")
    @Expose
    private Integer brandId;
    @SerializedName("departements_id")
    @Expose
    private Integer departementsId;
    @SerializedName("prix")
    @Expose
    private String prix;
    @SerializedName("quantite_en_stock")
    @Expose
    private Integer quantiteEnStock;
    @SerializedName("posted_by")
    @Expose
    private Integer postedBy;
    @SerializedName("en_vedette")
    @Expose
    private Integer enVedette;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDetailsProduct() {
        return detailsProduct;
    }

    public void setDetailsProduct(String detailsProduct) {
        this.detailsProduct = detailsProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getDepartementsId() {
        return departementsId;
    }

    public void setDepartementsId(Integer departementsId) {
        this.departementsId = departementsId;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Integer getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(Integer quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public Integer getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }

    public Integer getEnVedette() {
        return enVedette;
    }

    public void setEnVedette(Integer enVedette) {
        this.enVedette = enVedette;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

}
