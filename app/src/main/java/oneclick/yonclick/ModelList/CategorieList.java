package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import oneclick.yonclick.Model.Categorie;

public class CategorieList {
    @SerializedName("Brandprod")
    @Expose
    private ArrayList<Categorie> employee = null;

    public ArrayList<Categorie> getEmployee() {
        return employee;
    }

    public void setEmployee(ArrayList<Categorie> employee) {
        this.employee = employee;
    }
}
