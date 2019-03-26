package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Model.Abonnement;

public class AbonnementList {

    @SerializedName("data")
    @Expose
    private List<Abonnement> Services_Abonnement = null;


    public List<Abonnement> getServices_Abonnement() {
        return Services_Abonnement;
    }

    public void setServices_Abonnement(List<Abonnement> services_Abonnement) {
        Services_Abonnement = services_Abonnement;
    }
}
