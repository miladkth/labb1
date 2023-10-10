package ui.controllers;


import bo.entities.Product;
import bo.entities.User;
import bo.handlers.ProductService;
import bo.handlers.SessionService;
import bo.handlers.UserService;
import db.exceptions.DbException;
import ui.DTOs.ProductDTO;

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

        try {
            SessionService sessionService = new SessionService(req.getSession());
            //handle remove from cart
            if(req.getRequestURI().startsWith("/cart/remove/")){
                sessionService.removeFromCart(id);
                res.sendRedirect("/products");
                return;
            }

            //handle add to cart
            ProductDTO p = ProductService.getById(id);
            if(p == null){
                req.setAttribute("code", 404);
                req.setAttribute("message", "product with id "+ id + " not found!");
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
                return;
            }
            if(p.getQuantity() == 0){
                req.setAttribute("code", 400);
                req.setAttribute("message", "The product: "+ p.getTitle() + "is currently out of stock");
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
                return;
            }

            sessionService.addToCart(p);
            res.sendRedirect("/products");
        } catch (DbException e) {
            req.setAttribute("code", 404);
            req.setAttribute("message", e.getMessage());
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }
}