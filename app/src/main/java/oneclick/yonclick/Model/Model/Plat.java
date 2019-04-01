package oneclick.yonclick.Model.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Plat implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_plats")
    @Expose
    private String namePlats;
    @SerializedName("details_plats")
    @Expose
    private String detailsPlats;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("foodcategory_id")
    @Expose
    private Integer foodcategoryId;
    @SerializedName("prix")
    @Expose
    private String prix;
    @SerializedName("posted_by")
    @Expose
    private Integer postedBy;
    @SerializedName("cms_users_id")
    @Expose
    private Integer cmsUsersId;
    @SerializedName("en_vedette")
    @Expose
    private Integer enVedette;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePlats() {
        return namePlats;
    }

    public void setNamePlats(String namePlats) {
        this.namePlats = namePlats;
    }

    public String getDetailsPlats() {
        return detailsPlats;
    }

    public void setDetailsPlats(String detailsPlats) {
        this.detailsPlats = detailsPlats;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getFoodcategoryId() {
        return foodcategoryId;
    }

    public void setFoodcategoryId(Integer foodcategoryId) {
        this.foodcategoryId = foodcategoryId;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Integer getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }

    public Integer getCmsUsersId() {
        return cmsUsersId;
    }

    public void setCmsUsersId(Integer cmsUsersId) {
        this.cmsUsersId = cmsUsersId;
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

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

}
