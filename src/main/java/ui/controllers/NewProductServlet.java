package ui.controllers;

import bo.entities.Product;
import bo.handlers.FileUploadService;
import bo.handlers.ProductService;
import db.exceptions.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "NewProductUpload", value = "/admin/newProduct")
@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*11
)
public class NewProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String getTitle = req.getParameter("title");
        String getDesc = req.getParameter("description");
        String getPrice = req.getParameter("price");
        String getQty = req.getParameter("quantity");
        String getCategories = req.getParameter("categories");

        if (getTitle==null || getDesc==null || getPrice==null || getQty==null || getCategories==null) {
            req.setAttribute("error", "All field is required");
            req.getRequestDispatcher("login.jsp").forward(req,res);
            return;
        }


        int getQuantity;
        try {
            getQuantity = Integer.parseInt(getQty);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid quantity");
            req.getRequestDispatcher("login.jsp").forward(req,res);
            return;
        }

        float getPrice1;
        try {
            getPrice1 = Float.parseFloat(getPrice);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid price");
            req.getRequestDispatcher("login.jsp").forward(req,res);
            return;
        }

        ArrayList<String> list = ProductService.categoriesFromString(getCategories);
        Product product = new Product(getTitle,getDesc,getQuantity,getPrice1,"ewe",list);

        try {
            ProductService.addItem(product);
        } catch (DbException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("newProduct.jsp").forward(req,res);
            return;
        }


        //Part part = req.getPart("image");
        //String filename = getFilenameFromPart(part);
        //String accessUri = FileUploadService.Upload(filename, part.getInputStream());

        //res.getWriter().println(accessUri);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("newProduct.jsp").forward(req,res);
    }

    private String getFilenameFromPart(Part part){
        String contentDisposition = part.getHeader("content-disposition");
        int startI = contentDisposition.indexOf("filename=")+10;
        int endI = contentDisposition.length()-1;
        return contentDisposition.substring(startI,endI);
    }
}
