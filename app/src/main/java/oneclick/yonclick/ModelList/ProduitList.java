package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.Product;

public class ProduitList {
    @SerializedName("data")
    @Expose
    private ArrayList<Product> employee = null;

    public ArrayList<Product> getEmployee() {
        return employee;
    }

    public void setEmployee(ArrayList<Product> employee) {
        this.employee = employee;
    }
}
