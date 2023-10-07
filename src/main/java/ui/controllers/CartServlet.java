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

@WebServlet(name = "CartServlet", value = {"/cart/addToCart/*", "/cart/remove/*"})
public class CartServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);

        String l = req.getRequestURI();
        System.out.println(l);

        try {
            SessionService sessionService = new SessionService(req.getSession());
            if(l.startsWith("/cart/remove/")){
                sessionService.removeFromCart(id);
                res.sendRedirect("/products");
                return;
            }

            Product p = ProductService.getById(id);
            if(p == null){
                return;
            }
            sessionService.addToCart(p);
            res.sendRedirect("/products");
        } catch (DbException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }
}