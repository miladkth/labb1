package ui.controllers;

import bo.entities.User;
import bo.handlers.BoException;
import bo.handlers.OrderService;
import bo.handlers.SessionService;
import bo.handlers.UserService;
import db.exceptions.DbException;
import ui.DTOs.OrderDTO;
import ui.DTOs.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "UserManagementServlet", value = {"/admin/users","/admin/users/block/*","/admin/users/unblock/*", "/admin/user/role/*"})
public class UserManagementServlet extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        {
            //handle block user
            String url = req.getRequestURI();
            if(url.startsWith("/admin/users/block/") || url.startsWith("/admin/users/unblock/")){
                String pathInfo = req.getPathInfo();
                if(pathInfo == null || pathInfo == ""){
                    req.setAttribute("code", 400);
                    req.setAttribute("message", "No id provided");
                    req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
                    return;
                }
                String id = pathInfo.substring(1);
                try {
                    if(url.startsWith("/admin/users/block/")){
                        UserService.blockUser(id, false);
                    }else if(url.startsWith("/admin/users/unblock/")){
                        UserService.blockUser(id, true);
                    }
                    res.sendRedirect("/admin/users");
                    return;
                } catch (DbException e) {
                    throw new RuntimeException(e);
                }
            }

            if(url.startsWith("/admin/user/role/")){
                req.setAttribute("code", 405);
                req.setAttribute("message", "Get method is not defined to this url");
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
                return;
            }

            try {
                Collection<UserDTO> users = UserService.getAllUsers();
                req.setAttribute("users", users);

                req.getServletContext().getRequestDispatcher("/users.jsp").forward(req,res);
            } catch (DbException e) {
                req.setAttribute("code", 500);
                req.setAttribute("message", e.getMessage());
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            }

        }}

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String url = req.getRequestURI();
        if(url.startsWith("/admin/user/role/")){
            String pathInfo = req.getPathInfo();
            if(pathInfo == null || pathInfo == ""){
                req.setAttribute("code", 400);
                req.setAttribute("message", "No id provided");
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
                return;
            }
            String id = pathInfo.substring(1);
            try {
                String newRole = req.getParameter("role");
                if(newRole == null)
                {
                    req.setAttribute("code", 400);
                    req.setAttribute("message", "No id provided");
                    req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
                    return;
                }

                UserService.updateUserRole(id, newRole);
                res.sendRedirect("/admin/users");
                return;
            } catch (DbException e) {
                req.setAttribute("code", 500);
                req.setAttribute("message", e.getMessage());
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
                return;
            } catch (BoException e) {
                req.setAttribute("code", 400);
                req.setAttribute("message", e.getMessage());
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            }
        }

        req.setAttribute("code", 405);
        req.setAttribute("message", "Method not allowed");
        req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
    }
}
