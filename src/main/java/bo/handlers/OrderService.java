package bo.handlers;

import bo.entities.Order;
import db.exceptions.DbException;

import java.util.Collection;

public class OrderService {

    public static void createOrder(Order order) throws DbException {
        try {
            DbHandler db = new DbHandler();

            int a = db.orderDB.insertSingle(order);
            System.out.println("insertSingle " + a + "---------------");
        } catch (DbException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static Collection<Order> getAllOrders() throws DbException {
        try {
            DbHandler db = new DbHandler();

            return db.orderDB.getAll();
        } catch (DbException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
