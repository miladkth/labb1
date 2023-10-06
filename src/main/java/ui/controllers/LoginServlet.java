package ui.controllers;


import bo.entities.User;
import bo.handlers.FileUploadService;
import bo.handlers.SessionService;
import bo.handlers.UserService;
import db.exceptions.DbException;
import io.github.cdimascio.dotenv.Dotenv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        SessionService sessionService = new SessionService(req.getSession());
        User user = sessionService.getUser();
        System.out.println(user);
        if (user!=null) {
            req.getRequestDispatcher("upload.jsp").forward(req,res);
        }
        req.getRequestDispatcher("login.jsp").forward(req,res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String getEmail = req.getParameter("email");
        String getPassword = req.getParameter("password");

        try {
            User user = UserService.logInUserWithEmailAndPassword(getEmail,getPassword);

            if (user==null) {
                req.setAttribute("error", "Try again");
                req.getRequestDispatcher("login.jsp").forward(req,res);
                return;
            }

            SessionService sessionService = new SessionService(req.getSession());
            sessionService.saveUser(user);

            req.getRequestDispatcher("upload.jsp").forward(req,res);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}