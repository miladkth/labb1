package ui.controllers;

import bo.handlers.OrderService;
import bo.handlers.SessionService;
import db.exceptions.DbException;
import ui.DTOs.OrderDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "OrderServlet", value = {"/warehouse/orders", "/warehouse/order/*"})
public class OrderServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Collection<OrderDTO> orders = OrderService.getAllOrders();
            req.setAttribute("orders",orders);
            SessionService session = new SessionService(req.getSession());
            req.setAttribute("user", session.getUser());
            req.getServletContext().getRequestDispatcher("/orders.jsp").forward(req,res);
        } catch (DbException e) {
            req.setAttribute("code", 500);
            req.setAttribute("message", e.getMessage());
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);

        try {
            OrderService.toggleFulfilled(id);

            doGet(req, res);
        } catch (DbException e) {
            req.setAttribute("code", 500);
            req.setAttribute("message", e.getMessage());
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}