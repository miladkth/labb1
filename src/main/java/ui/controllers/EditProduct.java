package ui.controllers;


import bo.entities.Product;
import bo.entities.User;
import bo.handlers.FileUploadService;
import bo.handlers.ProductService;
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

@WebServlet(name = "EditProductServlet", value = "/admin/editProduct/*")
public class EditProduct extends HttpServlet {
    public void init() {}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);
        System.out.println(id);

        try {
            Product product = ProductService.getById(id);
            if(product == null)
                throw new ServletException("Product not found");

            req.setAttribute("product", product);
            req.getServletContext().getRequestDispatcher("/editProduct.jsp").forward(req,res);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(req.getParameter("field"));
        System.out.println(req.getParameter("newValue"));



        String pathInfo = req.getPathInfo();
        String id = pathInfo.substring(1);
        req.getParameter("newValue");
        String newVal = req.getParameter("newValue");
        try {
            ProductService.getById(id);
            String fieldName = req.getParameter("field");
            if(fieldName.equals("categories")){
                System.out.println("in cat");
                ProductService.toggleCategory(id, newVal);
            }else{
                ProductService.updateField(id, newVal, fieldName);
            }

            Product product = ProductService.getById(id);
            if(product == null)
                throw new ServletException("Product not found");

            req.setAttribute("product", product);
            req.getServletContext().getRequestDispatcher("/editProduct.jsp").forward(req,res);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }

    }
}