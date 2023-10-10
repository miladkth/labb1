package bo.entities;

import ui.DTOs.ProductDTO;

import java.util.ArrayList;
import java.util.UUID;

public class Product {
    private String id;
    private String description;
    private String title;
    private int quantity;
    private float price;
    private String imgUrl;
    private boolean isShown;
    private ArrayList<String> categories;

    public Product() {
        this.id = "";
        this.description = "";
        this.title = "";
        this.quantity = 12;
        this.price = 12;
        this.imgUrl = "";
        this.isShown = true;
    }
    public Product(ProductDTO product) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.title = product.getTitle();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.isShown = product.getIsShown();
        this.imgUrl = product.getImgUrl();
    }
    public Product(String id, String title, String description, int quantity, float price, String imgUrl, boolean isShown) {
        this.id = id;
        this.isShown = isShown;
        this.description = description;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.imgUrl = imgUrl;
        this.categories = new ArrayList<>();
    }
    public Product(String id, String title, String description, int quantity, float price, String imgUrl, ArrayList<String> categories, boolean isShown) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.imgUrl = imgUrl;
        this.isShown = isShown;
        this.categories = categories;
    }
    public Product(String title, String description, int quantity, float price, String imgUrl, ArrayList<String> categories) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.imgUrl = imgUrl;
        this.categories = categories;
        this.isShown = true;
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
    public boolean getIsShown() {
        return this.isShown;
    }

    public void setIsShown(boolean isShown) {
        this.isShown = isShown;
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
