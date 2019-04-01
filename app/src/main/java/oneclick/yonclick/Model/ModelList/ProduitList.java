package oneclick.yonclick.Model.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import oneclick.yonclick.Model.Model.Product;

public class ProduitList {
    @SerializedName("data")
    @Expose
    private List<Product> employee = null;


    public List<Product> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Product> employee) {
        this.employee = employee;
    }
}
