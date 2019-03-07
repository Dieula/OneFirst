package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import oneclick.yonclick.Model.Abonnement;
import oneclick.yonclick.Model.Ecolage;

public class EcolageList {

    @SerializedName("data")
    @Expose
    private ArrayList<Ecolage> Ecoles = null;

    public ArrayList<Ecolage> getEcoles() {
        return Ecoles;
    }

    public void setEcoles(ArrayList<Ecolage> ecoles) {
        Ecoles = ecoles;
    }
}
