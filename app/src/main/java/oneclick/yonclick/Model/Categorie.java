package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Categorie implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_departements")
    @Expose
    private String name_departements;
    @SerializedName("image")
    @Expose
    private String image;

    public Categorie(Integer id, String name_departements, String image) {
        this.id = id;
        this.name_departements = name_departements;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_departements() {
        return name_departements;
    }

    public void setName_departements(String name_departements) {
        this.name_departements = name_departements;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
