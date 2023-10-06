package bo.handlers;

import bo.entities.Product;
import bo.entities.User;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionService {
    private HttpSession session;
    public SessionService(HttpSession session) {
        this.session = session;
    }

    public void saveUser(User user) {
        this.session.setAttribute("user",user);
    }

    public User getUser() {
        return (User) this.session.getAttribute("user");
    }

    public void addToCart(Product product) {
        List<Product> cart = (List<Product>) this.session.getAttribute("cart");
        if (cart==null) {
            cart = new ArrayList<>();
        }

        for (Product p : cart) {
            if (p.getId().equals(product.getId())) {
                int qty = p.getQuantity();
                p.setQuantity(qty+1);
                return;
            }
        }
        product.setQuantity(1);
        cart.add(product);
        session.setAttribute("cart",cart);
    }

    public boolean removeFromCart(String productId) {
        List<Product> cart = (List<Product>) this.session.getAttribute("cart");
        for(Product p : cart) {
            if (p.getId().equals(productId)) {

                if (p.getQuantity()==1) {
                    cart.remove(p);
                    return true;
                }else {
                    int qty = p.getQuantity();
                    p.setQuantity(qty-1);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Product> getCart() {
        return (List<Product>) this.session.getAttribute("cart");
    }
}
