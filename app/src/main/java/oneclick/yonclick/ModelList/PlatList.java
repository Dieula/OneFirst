package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import oneclick.yonclick.Model.Ecolage;
import oneclick.yonclick.Model.Plat;

public class PlatList {

    @SerializedName("data")
    @Expose
    private ArrayList<Plat> Plats = null;

    public ArrayList<Plat> getPlats() {
        return Plats;
    }

    public void setPlats(ArrayList<Plat> plats) {
        Plats = plats;
    }
}
