package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import oneclick.yonclick.Model.Brand;

public class BrandList {
    @SerializedName("data")
    @Expose
    private List<Brand> data = null;

    public List<Brand> getData() {
        return data;
    }

    public void setData(List<Brand> data) {
        this.data = data;
    }


}
