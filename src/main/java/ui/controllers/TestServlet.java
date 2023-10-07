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
        try {
            ProductService.removeCategory("497b5dea-7386-4289-a74f-a9b44d240bfc", "22");
            ProductService.addCategory("497b5dea-7386-4289-a74f-a9b44d240bfc", "new cat");

            System.out.println("done");
        } catch (DbException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<h1>requrest to post/testservlet</h1>");
    }

    public void destroy() {
    }
}
