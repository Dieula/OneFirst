package oneclick.yonclick.Natcom;

public class RequestComptepayem {

    private String user_id;
    private String numero_tel;
    private String pin;
    private String nif;
    private String status;

    public RequestComptepayem(String user_id, String numero_tel, String pin, String nif, String status) {
        this.user_id = user_id;
        this.numero_tel = numero_tel;
        this.pin = pin;
        this.nif = nif;
        this.status = status;
    }

    public RequestComptepayem() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
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
