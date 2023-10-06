package ui.controllers;

import bo.entities.Order;
import bo.entities.Product;
import bo.entities.User;
import bo.handlers.OrderService;
import bo.handlers.ProductService;
import bo.handlers.UserService;
import db.OrderDB;
import db.exceptions.DbException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Milad";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("hej");
        Collection<Order> items = null;
        try {
            items = OrderService.getAllOrders();

            items.forEach(i -> {
                System.out.println("------");
                System.out.println(i.getId());
                i.getProducts().forEach(p -> {
                    System.out.println(p.getTitle());
                });
            });
            System.out.println(items.size());
        } catch (DbException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }



        //request.setAttribute("items", items);
        //request.getRequestDispatcher("/items.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<h1>requrest to post/testservlet</h1>");
    }

    public void destroy() {
    }
}
