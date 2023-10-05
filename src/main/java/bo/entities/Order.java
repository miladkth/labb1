package bo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private String id;
    private String userId;
    private boolean fullFilled;
    private String shippingAddress;
    private LocalDate createdAtDate;
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return this.products;
    }
    public void addProducts(ArrayList<Product> products){
        this.products.addAll(products);
    }
    public void addProduct(Product product){
        this.products.add(product);
    }

    public Order(String id, String userId, boolean fullFilled, String shippingAddress) {
        this.id = id;
        this.userId = userId;
        this.fullFilled = fullFilled;
        this.shippingAddress = shippingAddress;
    }


    public Order(String userId, String shippingAddress) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.fullFilled = false;
        this.shippingAddress = shippingAddress;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isFullFilled() {
        return fullFilled;
    }

    public void setFullFilled(boolean fullFilled) {
        this.fullFilled = fullFilled;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", fullFilled=" + fullFilled +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", createdAtDate=" + createdAtDate +
                '}';
    }
}
