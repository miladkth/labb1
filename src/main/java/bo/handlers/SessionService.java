package bo.handlers;

import bo.entities.Product;
import bo.entities.User;
import ui.DTOs.ProductDTO;
import ui.DTOs.UserDTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SessionService {
    private HttpSession session;
    public SessionService(HttpSession session) {
        this.session = session;
    }

    public void saveUser(UserDTO user) {
        this.session.setAttribute("user",user);
    }

    public UserDTO getUser() {
        return (UserDTO) this.session.getAttribute("user");
    }
    public void removeUser() {
        this.session.setAttribute("user", null);
    }

    public void addToCart(ProductDTO product) {
        List<ProductDTO> cart = (List<ProductDTO>) this.session.getAttribute("cart");
        if (cart==null) {
            cart = new ArrayList<>();
        }

        for (ProductDTO p : cart) {
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

    public void removeFromCart(String productId) {
        List<ProductDTO> cart = (List<ProductDTO>) this.session.getAttribute("cart");
        for(ProductDTO p : cart) {
            if (p.getId().equals(productId)) {
                if (p.getQuantity()==1) {
                    cart.remove(p);
                }else {
                    int qty = p.getQuantity();
                    p.setQuantity(qty-1);
                }
                return;
            }
        }
    }

    public List<ProductDTO> getCart() {
        List<ProductDTO> pList = (List<ProductDTO>) this.session.getAttribute("cart");
        return pList;
    }
    public void clearCart(){
        this.session.setAttribute("cart", null);
    }
}
