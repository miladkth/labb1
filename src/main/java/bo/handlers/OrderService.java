package bo.handlers;

import bo.entities.Order;
import bo.entities.Product;
import db.exceptions.DbException;
import ui.DTOs.OrderDTO;
import ui.DTOs.ProductDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderService {

    public static void createOrder(String userId, String shippingAddress, List<ProductDTO> cart) throws DbException, BoException {
        DbHandler db = null;
        try {
            // ProductDTO -> Product, ids
            ArrayList<Product> purgesItems = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            cart.forEach(item -> {
                purgesItems.add(new Product(item));
                ids.add(item.getId());
            });
            Order order = new Order(userId, shippingAddress, purgesItems);

            //start transaction
            db = new DbHandler();
            db.startTransaction();

            ArrayList<Product> products = new ArrayList<>(db.productDb.getManyById(ids));
            if(products.size() != purgesItems.size()){
                throw new BoException("Something gone wrong, all items not found");
            }

            // calculate new quantity values
            for (int i = 0; i<products.size(); i++){
                Product fromDb = products.get(i);
                for (int k = 0; k<products.size(); k++){
                    Product fromClient = purgesItems.get(k);
                    if(!fromClient.getId().equals(fromDb.getId()))
                        continue;
                    int res = fromDb.getQuantity() - fromClient.getQuantity();
                    if(res < 0){
                        throw new BoException("Item "+ fromDb.getTitle()+" in stock is not enough for the purge");
                    }
                    fromDb.setQuantity(res);
                }
            }

            //make order and update products quantities
            db.orderDB.insertSingle(order);
            for (int i = 0; i < products.size(); i++) {
                String id = products.get(i).getId();
                String qty = String.valueOf(products.get(i).getQuantity());
                db.productDb.updateOneField(id, qty, "quantity");
            }

            db.commitTransaction();
        } catch (Exception e) {
            if(db!=null){
                db.rollbackTransaction();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if(db!=null){
                db.endTransaction();
                db.release();
            }
        }
    }
    public static void toggleFulfilled(String id) throws DbException {
        DbHandler db = null;
        try{
            db = new DbHandler();
            db.startTransaction();

            boolean fulfilled = db.orderDB.getFulfillByID(id);
            db.orderDB.setFulfillByID(id, !fulfilled);

            db.commitTransaction();
        }catch (DbException e){
            db.rollbackTransaction();
            db.endTransaction();
            throw e;
        }finally {
            if (db!=null){
                db.release();
            }
        }
    }
    public static Collection<OrderDTO> getAllOrders() throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();

            Collection<Order> order = db.orderDB.getAll();
            Collection<OrderDTO> orderDto = new ArrayList<>();
            order.forEach(o->orderDto.add(new OrderDTO(o)));
            return orderDto;
        } catch (DbException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }
}
