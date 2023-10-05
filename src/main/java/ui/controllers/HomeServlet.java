package ui.controllers;


import bo.handlers.FileUploadService;
import io.github.cdimascio.dotenv.Dotenv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //FileUploadService.Upload();
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        Map<String,String> env = System.getenv();

        env.forEach((String k, String v) -> {
            System.out.println(k+" : "+v);
        });
        String username = System.getenv("DB_USER_NAME");
        String pwd = System.getenv("DB_PASSWORD");
        String url = System.getenv("DB_URL_STRING");


        out.println("<h1>requrest to get /homeservlet upload test "+username+pwd+url+"</h1>");
    }
}
