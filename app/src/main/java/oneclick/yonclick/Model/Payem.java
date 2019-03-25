package oneclick.yonclick.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Payem implements Serializable {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("numero_tel")
    @Expose
    private String numeroTel;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("nif")
    @Expose
    private String nif;
    @SerializedName("status")
    @Expose
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
