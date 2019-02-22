package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ecolage {

    @SerializedName("idEcoles")
    @Expose
    private Integer idEcoles;
    @SerializedName("NomEcoles")
    @Expose
    private String NomEcoles;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getIdEcoles() {
        return idEcoles;
    }

    public void setIdEcoles(Integer idEcoles) {
        this.idEcoles = idEcoles;
    }

    public String getNomEcoles() {
        return NomEcoles;
    }

    public void setNomEcoles(String nomEcoles) {
        NomEcoles = nomEcoles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
