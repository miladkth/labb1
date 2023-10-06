package bo.handlers;

import bo.entities.Order;
import bo.entities.Product;
import db.exceptions.DbException;
import java.util.Collection;
import java.util.List;

public class OrderService {

    public static void createOrder(Order order) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();
            int[] a = db.orderDB.insertSingle(order);
        } catch (DbException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }

    public static Collection<Order> getAllOrders() throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();
            return db.orderDB.getAll();
        } catch (DbException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }
}
