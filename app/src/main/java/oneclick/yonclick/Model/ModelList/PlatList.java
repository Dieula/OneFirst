package oneclick.yonclick.Model.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import oneclick.yonclick.Model.Model.Plat;

public class PlatList {


    @SerializedName("data")
    @Expose
    private List<Plat> data = null;

    public List<Plat> getData() {
        return data;
    }

    public void setData(List<Plat> data) {
        this.data = data;
    }

}
