package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Categorie implements Serializable{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_departements")
    @Expose
    private String nameDepartements;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("posted_by")
    @Expose
    private Integer postedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("get_category_with_produits")
    @Expose
    private List<GetCategoryWithProduit> getCategoryWithProduits;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameDepartements() {
        return nameDepartements;
    }

    public void setNameDepartements(String nameDepartements) {
        this.nameDepartements = nameDepartements;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
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

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<GetCategoryWithProduit> getGetCategoryWithProduits() {
        return getCategoryWithProduits;
    }

    public void setGetCategoryWithProduits(List<GetCategoryWithProduit> getCategoryWithProduits) {
        this.getCategoryWithProduits = getCategoryWithProduits;
    }

}
