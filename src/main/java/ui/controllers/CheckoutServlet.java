package ui.controllers;

import bo.entities.Order;
import bo.entities.Product;
import bo.handlers.BoException;
import bo.handlers.OrderService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        SessionService session = new SessionService(req.getSession());


        UserDTO user = session.getUser();
        if(user == null){
            req.setAttribute("code", 403);
            req.setAttribute("message", "Login to perform action");
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }
        System.out.println("pass user null check");

        List<ProductDTO> cart = session.getCart();
        if(cart.size() == 0 || cart == null){
            req.setAttribute("code", 400);
            req.setAttribute("message", "Cart is empty");
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }
        System.out.println("pass cart empty check");
        System.out.println(cart.size());

        try {
            OrderService.createOrder(user.getId(), "shipping address", cart);
            session.clearCart();
            req.getServletContext().getRequestDispatcher("/PaymentSuccess.jsp").forward(req,res);
        } catch (DbException e) {
            req.setAttribute("code", 500);
            req.setAttribute("message", "Something has gone wrong, try again!");
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        } catch (BoException e) {
            req.setAttribute("code", 403);
            req.setAttribute("message", e.getMessage());
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}
