package ui.controllers;


import bo.entities.Product;
import bo.entities.User;
import bo.handlers.ProductService;
import bo.handlers.SessionService;
import bo.handlers.UserService;
import db.exceptions.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart/addToCart/*")
public class CartServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);

        try {
            Product p = ProductService.getById(id);

            if(p == null){
                System.out.println("product is null");
                return;
            }

            SessionService sessionService = new SessionService(req.getSession());


            sessionService.addToCart(p);


            List<Product> cart = sessionService.getCart();
            System.out.println("----------------------------");
            cart.forEach(c -> {
                System.out.println(c);
            });


            System.out.println("test");

        } catch (DbException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

        System.out.println(pathInfo);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }
}