package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import oneclick.yonclick.Model.Abonnement;

public class AbonnementList {

    @SerializedName("data")
    @Expose
    private ArrayList<Abonnement> Services_Abonnement = null;


    public ArrayList<Abonnement> getServices_Abonnement() {
        return Services_Abonnement;
    }

    public void setServices_Abonnement(ArrayList<Abonnement> services_Abonnement) {
        Services_Abonnement = services_Abonnement;
    }
}
