package oneclick.yonclick.Model.Commande;

public class RequestCommandes {

    private String order_number;
    private String user_id;
    private String id_achats;
    private String details_product;
    private String payement_id;
    private String type_order;
    private String id_posted_by;
    private String delivery_date;
    private String total_discount;
    private String total_shipping;
    private String statut_commandes;
    private String total_tax;
    private String total;
    private String city_departement_id;
    private String types_livraison;
    private String adresse_order;
    private String longitude;
    private String latitude;

    public RequestCommandes(String order_number, String user_id, String id_achats, String details_product, String payement_id,
                            String type_order, String id_posted_by, String delivery_date, String total_discount, String total_shipping,
                            String statut_commandes, String total_tax, String total, String city_departement_id, String types_livraison,
                            String adresse_order, String longitude, String latitude) {
        this.order_number = order_number;
        this.user_id = user_id;
        this.id_achats = id_achats;
        this.details_product = details_product;
        this.payement_id = payement_id;
        this.type_order = type_order;
        this.id_posted_by = id_posted_by;
        this.delivery_date = delivery_date;
        this.total_discount = total_discount;
        this.total_shipping = total_shipping;
        this.statut_commandes = statut_commandes;
        this.total_tax = total_tax;
        this.total = total;
        this.city_departement_id = city_departement_id;
        this.types_livraison = types_livraison;
        this.adresse_order = adresse_order;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public RequestCommandes() {
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId_achats() {
        return id_achats;
    }

    public void setId_achats(String id_achats) {
        this.id_achats = id_achats;
    }

    public String getDetails_product() {
        return details_product;
    }

    public void setDetails_product(String details_product) {
        this.details_product = details_product;
    }

    public String getPayement_id() {
        return payement_id;
    }

    public void setPayement_id(String payement_id) {
        this.payement_id = payement_id;
    }

    public String getType_order() {
        return type_order;
    }

    public void setType_order(String type_order) {
        this.type_order = type_order;
    }

    public String getId_posted_by() {
        return id_posted_by;
    }

    public void setId_posted_by(String id_posted_by) {
        this.id_posted_by = id_posted_by;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(String total_discount) {
        this.total_discount = total_discount;
    }

    public String getTotal_shipping() {
        return total_shipping;
    }

    public void setTotal_shipping(String total_shipping) {
        this.total_shipping = total_shipping;
    }

    public String getStatut_commandes() {
        return statut_commandes;
    }

    public void setStatut_commandes(String statut_commandes) {
        this.statut_commandes = statut_commandes;
    }

    public String getTotal_tax() {
        return total_tax;
    }

    public void setTotal_tax(String total_tax) {
        this.total_tax = total_tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCity_departement_id() {
        return city_departement_id;
    }

    public void setCity_departement_id(String city_departement_id) {
        this.city_departement_id = city_departement_id;
    }

    public String getTypes_livraison() {
        return types_livraison;
    }

    public void setTypes_livraison(String types_livraison) {
        this.types_livraison = types_livraison;
    }

    public String getAdresse_order() {
        return adresse_order;
    }

    public void setAdresse_order(String adresse_order) {
        this.adresse_order = adresse_order;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
