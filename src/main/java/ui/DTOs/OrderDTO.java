package ui.DTOs;

import bo.entities.Order;
import java.util.ArrayList;

public class OrderDTO {
    private String id;
    private String userId;
    private boolean fullFilled;
    private String shippingAddress;
    private ArrayList<ProductDTO> products;

    public OrderDTO(Order order) {
        if(order == null)
            return;
        this.id = order.getId();
        this.userId = order.getUserId();
        this.fullFilled = order.isFullFilled();
        this.shippingAddress = order.getShippingAddress();
        this.products = new ArrayList<>();
        order.getProducts().forEach(p->this.products.add(new ProductDTO(p)));
    }
    public ArrayList<ProductDTO> getProducts() {
        return this.products;
    }

    public void addProducts(ArrayList<ProductDTO> products){
        this.products.addAll(products);
    }

    public void addProduct(ProductDTO product){
        this.products.add(product);
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
                '}';
    }
}
