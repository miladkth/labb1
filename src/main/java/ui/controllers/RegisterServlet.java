package ui.controllers;


import bo.entities.User;
import bo.handlers.UserService;
import db.exceptions.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req,res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String getName = req.getParameter("name");
        String getEmail = req.getParameter("email");
        String getPassword = req.getParameter("password");
        String getConfirmPassword = req.getParameter("confirmpassword");

        if (!getPassword.equals(getConfirmPassword)) {
            req.setAttribute("error", "password don't match");
            req.getRequestDispatcher("register.jsp").forward(req,res);
            return;
        }

        try {
            User user = UserService.createUserWithEmailAndPassword(getName,getEmail,getPassword);
            req.getRequestDispatcher("login.jsp").forward(req,res);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}