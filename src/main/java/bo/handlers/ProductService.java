package bo.handlers;

import bo.entities.Product;
import db.DbContext;
import db.exceptions.DbException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ProductService{
    public static void addItem (Product newItem) throws DbException {
        DbHandler db = new DbHandler();
        db.productDb.insertSingle(newItem);
    }
    public static Collection<Product> getItems () throws DbException{
        DbHandler db = new DbHandler();

        Collection<Product> items;
        try{
            //db.startTransaction();

            //db.productDb.insertSingle(new Product("product 1", "description for product 1", 11, 99.91F, "html.noimge"));
            //db.productDb.insertSingle(new Product("product 2", "description for product 2", 12, 99.92F, "html.noimge"));
            //db.productDb.insertSingle(new Product("product 3", "description for product 3", 13, 99.93F, "html.noimge"));

            //db.commitTransaction();
            items = db.productDb.getAll();
        }catch (DbException e){
            //db.rollbackTransaction();
            throw e;
        } finally {
            //db.endTransaction();
            db.release();
        }
        return items;
    }
    public static ArrayList<String> categoriesFromString(String categoryString){
            String[] result = categoryString.split(",");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(result));
            ArrayList<String> slist = new ArrayList<>();
            list.forEach(l -> slist.add(l.trim()));
            return slist;
    }
}
