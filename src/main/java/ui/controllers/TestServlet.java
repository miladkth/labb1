package ui.controllers;

/*import java.io.*;*/

import bo.entities.Order;
import bo.entities.Product;
import bo.entities.User;
import bo.handlers.OrderService;
import bo.handlers.ProductService;
import bo.handlers.UserService;
import db.DbContext;
import db.OrderDB;
import db.exceptions.DbException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Milad";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("hej");
        Collection<Product> items = null;
        try {
            items = ProductService.getItems();
            User user = UserService.getUserInfo("c21f765f-236b-425e-9267-271e67fdd96e");
            System.out.println("Get user ---------------");

            List<Product> products = new ArrayList<>();
            Product p1 = new Product("8b326372-692e-4b75-8836-ea3fc1b4bef9", "taquang","milad",5, (float) 69.90,"");
            Product p2 = new Product("c79be117-8899-455c-92be-6b7d4b3c83c8", "taquang","milad",3, (float) 69.90,"");
            products.add(p1);
            products.add(p2);

            /*OrderService.createOrder(new Order(user.getId(), "hälsovägen 1"), products);
            System.out.println("Created order--------------");

            Collection orders = OrderService.getAllOrders();
            System.out.println("Get order ------------------");

            orders.forEach(order -> {
                System.out.println(order.toString());
            });

            System.out.println("done creating user");*/
            /*<String> cat = new ArrayList<>();
            cat.add("book1");
            cat.add("learning");
            cat.add("cat1");
            Product p = new Product("book5","descr för b5",15,(float)38.95,"url",cat);
            ProductService.addItem(p);*/
        } catch (DbException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


        request.setAttribute("items", items);
        request.getRequestDispatcher("/items.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<h1>requrest to post/testservlet</h1>");
    }

    public void destroy() {
    }
}
