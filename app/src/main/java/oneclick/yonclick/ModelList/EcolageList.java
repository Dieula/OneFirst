package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Model.Abonnement;
import oneclick.yonclick.Model.Ecolage;

public class EcolageList {

    @SerializedName("data")
    @Expose
    private List<Ecolage> Ecoles = null;

    public List<Ecolage> getEcoles() {
        return Ecoles;
    }

    public void setEcoles(List<Ecolage> ecoles) {
        Ecoles = ecoles;
    }
}
