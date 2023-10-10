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

@WebServlet(name = "RegisterServlet", value = "/user/register")
public class RegisterServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        SessionService session = new SessionService(req.getSession());
        req.setAttribute("user", session.getUser());
        req.getServletContext().getRequestDispatcher("/register.jsp").forward(req,res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String getName = req.getParameter("name");
        String getEmail = req.getParameter("email");
        String getRole = req.getParameter("role");
        String getPassword = req.getParameter("password");
        String getConfirmPassword = req.getParameter("confirmpassword");

        if (getName==null || getEmail==null || getPassword==null || getConfirmPassword==null
                || getName.equals("") || getEmail.equals("") || getPassword.equals("") || getConfirmPassword.equals("")) {
            req.setAttribute("error", "All field is required");
            req.getServletContext().getRequestDispatcher("/register.jsp").forward(req,res);
            return;
        }

        String roleTemp = "customer";
        SessionService session = new SessionService(req.getSession());
        UserDTO user = session.getUser();

        if(getRole != null && (getRole.equals("warehouse") || getRole.equals("admin"))){
            if(!user.getRole().equals("admin")){
                req.setAttribute("code", 403);
                req.setAttribute("message", "Permission denied, no access!");
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
                return;
            }else{
                roleTemp = getRole;
            }
        }

        if (!getPassword.equals(getConfirmPassword)) {
            req.setAttribute("error", "password don't match, try again");
            req.getServletContext().getRequestDispatcher("/register.jsp").forward(req,res);
            return;
        }

        try {
            UserService.createUserWithEmailAndPassword(getName,getEmail,getPassword, roleTemp);
            if(user.getRole().equals("admin")){
                req.setAttribute("info", "User created successfully");
                req.getServletContext().getRequestDispatcher("/register.jsp").forward(req,res);
                return;
            }
                req.getServletContext().getRequestDispatcher("/login.jsp").forward(req,res);
        } catch (DbException e) {
            req.setAttribute("code", 500);
            req.setAttribute("message", e.getMessage());
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}