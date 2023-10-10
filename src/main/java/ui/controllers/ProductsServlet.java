package ui.controllers;

import bo.handlers.ProductService;
import bo.handlers.SessionService;
import db.exceptions.DbException;
import ui.DTOs.ProductDTO;
import ui.DTOs.UserDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ProductsServlet", value = "/products")
public class ProductsServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String category = req.getParameter("category");
        System.out.println(category);

        System.out.println();
        try {
            SessionService session = new SessionService(req.getSession());
            UserDTO user = session.getUser();
            req.setAttribute("user", user);

            Collection<ProductDTO> products;
            if(category!=null){
                if(user.getRole().equals("customer")){
                    products = ProductService.getByCategory(category, false);
                }else {
                    products = ProductService.getByCategory(category, true);
                }
            }else{
                if(user.getRole().equals("customer")) {
                    products = ProductService.getAll(false);
                }else {
                    products = ProductService.getAll(true);
                }
            }

            req.setAttribute("products", products);

            Collection<ProductDTO> cart = session.getCart();
            req.setAttribute("cart", cart);


            Collection<String> categories = ProductService.getAllCategories();
            req.setAttribute("categories", categories);

            req.getRequestDispatcher("products.jsp").forward(req,res);
        } catch (DbException e) {
            req.setAttribute("code", 500);
            req.setAttribute("message", e.getMessage());
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        }

    }

}
