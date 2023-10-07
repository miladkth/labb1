package ui.controllers;

import bo.entities.Product;
import bo.entities.User;
import bo.handlers.ProductService;
import bo.handlers.SessionService;
import db.exceptions.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ProductsServlet", value = "/products")
public class ProductsServlet extends HomeServlet{
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            Collection<Product> products = ProductService.getAll();
            req.setAttribute("products", products);

            SessionService session = new SessionService(req.getSession());
            Collection<Product> cart = session.getCart();
            req.setAttribute("cart", cart);

            User user = session.getUser();
            req.setAttribute("user", user);

            req.getRequestDispatcher("products.jsp").forward(req,res);
        } catch (DbException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

    }
}
