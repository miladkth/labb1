package ui.DTOs;

import bo.entities.Product;
import java.util.ArrayList;

public class ProductDTO {
    private String id;
    private String description;
    private String title;
    private int quantity;
    private float price;
    private String imgUrl;
    private boolean isShown;
    private ArrayList<String> categories;

    public ProductDTO(Product product) {
        if(product == null)
            return;
        this.id = product.getId();
        this.description = product.getDescription();
        this.title = product.getTitle();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
        this.categories = new ArrayList<>();
        this.isShown = product.getIsShown();
        product.getCategories().forEach(c->this.categories.add(c));
    }
    public void setIsShown(boolean isShown){
        this.isShown = isShown;
    }
    public boolean getIsShown(){
        return this.isShown;
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
    public void addCategory(String category){
        if(this.categories == null)
            this.categories = new ArrayList<>();
        this.categories.add(category);
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public ArrayList<String> getCategories() {
        return categories;
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
