package ui.controllers;


import bo.entities.Product;
import bo.handlers.ProductService;
import bo.handlers.SessionService;
import db.exceptions.DbException;
import ui.DTOs.ProductDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProductServlet", value = "/warehouse/editProduct/*")
public class EditProduct extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);
        System.out.println(id);

        try {
            ProductDTO product = ProductService.getById(id);
            if(product == null){
                req.setAttribute("code", 404);
                req.setAttribute("message", "product with id "+ id + " not found!");
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
                return;
            }

            req.setAttribute("product", product);
            SessionService session = new SessionService(req.getSession());
            req.setAttribute("user", session.getUser());
            req.getServletContext().getRequestDispatcher("/editProduct.jsp").forward(req,res);
        } catch (DbException e) {
            req.setAttribute("code", 500);
            req.setAttribute("message", e.getMessage());
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);
        req.getParameter("newValue");
        String newVal = req.getParameter("newValue");
        try {
            ProductService.getById(id);
            String fieldName = req.getParameter("field");
            if(fieldName.equals("categories")){
                ProductService.toggleCategory(id, newVal);
            }else if(fieldName.equals("isShown")){
                ProductService.setIsShown(id, newVal.equals("true"));
            }
            else{
                ProductService.updateField(id, newVal, fieldName);
            }

            ProductDTO product = ProductService.getById(id);
            if(product == null){
                req.setAttribute("code", 404);
                req.setAttribute("message", "product with id "+ id + " not found!");
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            }

            req.setAttribute("product", product);
            req.getServletContext().getRequestDispatcher("/editProduct.jsp").forward(req,res);
        } catch (DbException e) {
            req.setAttribute("code", 404);
            req.setAttribute("message", e.getMessage());
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}