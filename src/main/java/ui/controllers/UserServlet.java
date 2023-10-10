package ui.controllers;


import bo.handlers.SessionService;
import bo.handlers.UserService;
import db.exceptions.DbException;
import ui.DTOs.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = {"/user/login", "/user/logout"})
public class UserServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        SessionService sessionService = new SessionService(req.getSession());
        UserDTO user = sessionService.getUser();

        //logout user
        if(req.getRequestURI().equals("/user/logout")){
            sessionService.removeUser();
            req.getServletContext().getRequestDispatcher("/login.jsp").forward(req,res);
            return;
        }

        // user already loged in
        if (user!=null) {
            res.sendRedirect("/products");
            return;
        }
        req.getServletContext().getRequestDispatcher("/login.jsp").forward(req,res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String getEmail = req.getParameter("email");
        String getPassword = req.getParameter("password");

        if (getEmail==null || getPassword==null
                || getEmail.equals("") || getPassword.equals("")) {
            req.setAttribute("error", "All field is required");
            req.getServletContext().getRequestDispatcher("/login.jsp").forward(req,res);
            return;
        }

        try {
            UserDTO user = UserService.logInUserWithEmailAndPassword(getEmail,getPassword);

            if (user==null) {
                req.setAttribute("error", "Try again");
                req.getServletContext().getRequestDispatcher("/login.jsp").forward(req,res);
                return;
            }

            SessionService sessionService = new SessionService(req.getSession());
            sessionService.saveUser(user);

            res.sendRedirect("/products");
        } catch (DbException e) {
            req.setAttribute("code", 500);
            req.setAttribute("message", e.getMessage());
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}