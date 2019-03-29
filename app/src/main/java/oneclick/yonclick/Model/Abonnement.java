package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Abonnement implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_subscription")
    @Expose
    private String name_subscription;
    @SerializedName("image")
    @Expose
    private String image;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_subscription() {
        return name_subscription;
    }

    public void setName_subscription(String name_subscription) {
        this.name_subscription = name_subscription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
