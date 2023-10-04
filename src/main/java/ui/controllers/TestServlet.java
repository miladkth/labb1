package ui.controllers;

/*import java.io.*;*/

import bo.entities.Product;
import bo.entities.User;
import bo.handlers.ProductService;
import bo.handlers.UserService;
import db.DbContext;
import db.exceptions.DbException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Milad";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Collection<Product> items = null;
        try {
            items = ProductService.getItems();
            UserService.getUserInfo("c21f765f-236b-425e-9267-271e67fdd96e");
            System.out.println("done creating user");
        } catch (DbException e) {
            throw new RuntimeException(e);
        }


        request.setAttribute("items", items);
        request.getRequestDispatcher("/items.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<h1>requrest to post/testservlet</h1>");
    }

    public void destroy() {
    }
}
