package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Magasin {


    @SerializedName("id")
    @Expose
    private Integer id;


    @SerializedName("name_busness")
    @Expose
    private Integer name_busness;


    @SerializedName("image_compagnie")
    @Expose
    private Integer image_compagnie;

    @SerializedName("adresse")
    @Expose
    private String adresse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName_busness() {
        return name_busness;
    }

    public void setName_busness(Integer name_busness) {
        this.name_busness = name_busness;
    }

    public Integer getImage_compagnie() {
        return image_compagnie;
    }

    public void setImage_compagnie(Integer image_compagnie) {
        this.image_compagnie = image_compagnie;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
