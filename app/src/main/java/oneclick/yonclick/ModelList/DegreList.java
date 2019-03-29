package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DegreList {
    @SerializedName("data")
    @Expose
    private List<DegreList> data = null;

    public List<DegreList> getData() {
        return data;
    }

    public void setData(List<DegreList> data) {
        this.data = data;
    }
}
