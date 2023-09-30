package ui.controllers;

/*import java.io.*;*/
import db.DbManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "TestServervlet", value = "/TestServervlet")
public class TestServervlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Milad";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection con = DbManager.getConnection();

        response.setContentType("text/html");

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
