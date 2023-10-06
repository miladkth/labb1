package ui.controllers;

import bo.entities.Order;
import bo.entities.Product;
import bo.handlers.OrderService;
import bo.handlers.ProductService;
import db.exceptions.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/warehouse/orders")
public class OrderServlet extends HomeServlet{
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            Collection<Order> orders = OrderService.getAllOrders();

            req.setAttribute("orders",orders);

        } catch (DbException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

        req.getRequestDispatcher("orders.jsp").forward(req,res);
    }
}