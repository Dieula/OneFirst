package oneclick.yonclick.Model.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Degre {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("degree")
    @Expose
    private String degree;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("class_with_frais")
    @Expose
    private List<ClassWithFrai> classWithFrais = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ClassWithFrai> getClassWithFrais() {
        return classWithFrais;
    }

    public void setClassWithFrais(List<ClassWithFrai> classWithFrais) {
        this.classWithFrais = classWithFrais;
    }

}
