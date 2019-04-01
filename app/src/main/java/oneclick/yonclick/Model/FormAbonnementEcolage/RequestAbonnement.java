package oneclick.yonclick.Model.FormAbonnementEcolage;

public class RequestAbonnement {


    private Integer nombre_mois;
    private String titulaire_account;
    private Integer plan_id;
    private Integer compagnie_sub_id;
    private String numero_account;
    private String montant;


    public RequestAbonnement(Integer nombre_mois, String titulaire_account, Integer plan_id, Integer compagnie_sub_id, String numero_account, String montant) {
        this.nombre_mois = nombre_mois;
        this.titulaire_account = titulaire_account;
        this.plan_id = plan_id;
        this.compagnie_sub_id = compagnie_sub_id;
        this.numero_account = numero_account;
        this.montant = montant;
    }

    public RequestAbonnement() {

    }

    public Integer getNombre_mois() {
        return nombre_mois;
    }

    public void setNombre_mois(Integer nombre_mois) {
        this.nombre_mois = nombre_mois;
    }

    public String getTitulaire_account() {
        return titulaire_account;
    }

    public void setTitulaire_account(String titulaire_account) {
        this.titulaire_account = titulaire_account;
    }

    public Integer getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(Integer plan_id) {
        this.plan_id = plan_id;
    }

    public Integer getCompagnie_sub_id() {
        return compagnie_sub_id;
    }

    public void setCompagnie_sub_id(Integer compagnie_sub_id) {
        this.compagnie_sub_id = compagnie_sub_id;
    }

    public String getNumero_account() {
        return numero_account;
    }

    public void setNumero_account(String numero_account) {
        this.numero_account = numero_account;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
}
