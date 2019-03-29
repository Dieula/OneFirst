package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassWithFrai {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("degree_id")
    @Expose
    private Integer degreeId;
    @SerializedName("frais_mensuel")
    @Expose
    private Integer fraisMensuel;
    @SerializedName("frais_ecolage")
    @Expose
    private Integer fraisEcolage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public Integer getFraisMensuel() {
        return fraisMensuel;
    }

    public void setFraisMensuel(Integer fraisMensuel) {
        this.fraisMensuel = fraisMensuel;
    }

    public Integer getFraisEcolage() {
        return fraisEcolage;
    }

    public void setFraisEcolage(Integer fraisEcolage) {
        this.fraisEcolage = fraisEcolage;
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


}
