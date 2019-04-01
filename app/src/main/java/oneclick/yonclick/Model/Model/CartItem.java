package oneclick.yonclick.Model.Model;

public class CartItem {

    public int productId;
    public String name;
    public String images;
    public float price;
    public int quantity;
    public String attribute;
    public int isSelected;

    public CartItem(int productId, String name, String images, float price, int quantity, String attribute, int isSelected){
        this.productId = productId;
        this.name = name;
        this.images = images;
        this.price = price;
        this.quantity = quantity;
        this.attribute = attribute;
        this.isSelected = isSelected;
    }
}
