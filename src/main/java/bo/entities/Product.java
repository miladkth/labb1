package bo.entities;

import java.util.UUID;

public class Product {
    private String id;
    private String description;
    private String title;
    private int quantity;
    private float price;
    private String imgUrl;
    public Product() {
        this.id = "";
        this.description = "";
        this.title = "";
        this.quantity = 12;
        this.price = 12;
        this.imgUrl = "";
    }
    public Product(String id, String title, String description, int quantity, float price, String imgUrl) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.imgUrl = imgUrl;
    }
    public Product(String title, String description, int quantity, float price, String imgUrl) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
