package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categorie {

    @SerializedName("idbrand")
    @Expose
    private Integer idbrand;
    @SerializedName("Nombrand")
    @Expose
    private String Nombrand;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getIdbrand() {
        return idbrand;
    }

    public void setIdbrand(Integer idbrand) {
        this.idbrand = idbrand;
    }

    public String getNombrand() {
        return Nombrand;
    }

    public void setNombrand(String nombrand) {
        Nombrand = nombrand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
