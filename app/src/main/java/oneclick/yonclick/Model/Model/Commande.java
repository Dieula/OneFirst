package oneclick.yonclick.Model.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Commande implements Serializable {

    @SerializedName("order_number")
    @Expose
    private String orderNumber;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("id_achats")
    @Expose
    private String idAchats;
    @SerializedName("details_product")
    @Expose
    private String detailsProduct;
    @SerializedName("payement_id")
    @Expose
    private String payementId;
    @SerializedName("type_order")
    @Expose
    private String typeOrder;
    @SerializedName("id_posted_by")
    @Expose
    private String idPostedBy;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;
    @SerializedName("total_discount")
    @Expose
    private String totalDiscount;
    @SerializedName("total_shipping")
    @Expose
    private String totalShipping;
    @SerializedName("statut_commandes")
    @Expose
    private Integer statutCommandes;
    @SerializedName("total_tax")
    @Expose
    private String totalTax;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("city_departement_id")
    @Expose
    private String cityDepartementId;
    @SerializedName("types_livraison")
    @Expose
    private String typesLivraison;
    @SerializedName("adresse_order")
    @Expose
    private String adresseOrder;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdAchats() {
        return idAchats;
    }

    public void setIdAchats(String idAchats) {
        this.idAchats = idAchats;
    }

    public String getDetailsProduct() {
        return detailsProduct;
    }

    public void setDetailsProduct(String detailsProduct) {
        this.detailsProduct = detailsProduct;
    }

    public String getPayementId() {
        return payementId;
    }

    public void setPayementId(String payementId) {
        this.payementId = payementId;
    }

    public String getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
    }

    public String getIdPostedBy() {
        return idPostedBy;
    }

    public void setIdPostedBy(String idPostedBy) {
        this.idPostedBy = idPostedBy;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getTotalShipping() {
        return totalShipping;
    }

    public void setTotalShipping(String totalShipping) {
        this.totalShipping = totalShipping;
    }

    public Integer getStatutCommandes() {
        return statutCommandes;
    }

    public void setStatutCommandes(Integer statutCommandes) {
        this.statutCommandes = statutCommandes;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCityDepartementId() {
        return cityDepartementId;
    }

    public void setCityDepartementId(String cityDepartementId) {
        this.cityDepartementId = cityDepartementId;
    }

    public String getTypesLivraison() {
        return typesLivraison;
    }

    public void setTypesLivraison(String typesLivraison) {
        this.typesLivraison = typesLivraison;
    }

    public String getAdresseOrder() {
        return adresseOrder;
    }

    public void setAdresseOrder(String adresseOrder) {
        this.adresseOrder = adresseOrder;
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