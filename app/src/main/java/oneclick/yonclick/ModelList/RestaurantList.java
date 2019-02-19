package oneclick.yonclick.ModelList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import oneclick.yonclick.Model.Employe;

public class RestaurantList {
    @SerializedName("employee")
    @Expose
    private ArrayList<Employe> employee = null;

    public ArrayList<Employe> getEmployee() {
        return employee;
    }

    public void setEmployee(ArrayList<Employe> employee) {
        this.employee = employee;
    }
}
