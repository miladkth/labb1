package ui.controllers;

/*import java.io.*;*/
import bo.entities.Item;
import bo.handlers.ItemService;
import db.DbContext;
import db.DbManager;
import db.exceptions.DbException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collection;

@WebServlet(name = "TestServervlet", value = "/TestServervlet")
public class TestServervlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Milad";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Collection<Item> items = ItemService.getItems();

            items.forEach(i ->{
                System.out.println(i.toString());
            });

        } catch (DbException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        request.getSession().setAttribute("test", "this is test session");
        PrintWriter out = response.getWriter();
        out.println("<h1>requrest to get/testservlet</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<h1>requrest to post/testservlet</h1>");
    }

    public void destroy() {
    }
}
