package ui.controllers;

import bo.handlers.FileUploadService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "TestServlet", value = "/new-product-upload")
@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*11
)
public class NewProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        String filename = getFilenameFromPart(part);
        String accessUri = FileUploadService.Upload(filename, part.getInputStream());

        response.getWriter().println(accessUri);
    }

    private String getFilenameFromPart(Part part){
        String contentDisposition = part.getHeader("content-disposition");
        int startI = contentDisposition.indexOf("filename=")+10;
        int endI = contentDisposition.length()-1;
        return contentDisposition.substring(startI,endI);
    }
}
