package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

import oneclick.yonclick.Model.Magasin;

public class MagasinsList {

    @SerializedName("data")
    @Expose
    private List<Magasin> data = null;

    public List<Magasin> getData() {
        return data;
    }

    public void setData(List<Magasin> data) {
        this.data = data;
    }

}
