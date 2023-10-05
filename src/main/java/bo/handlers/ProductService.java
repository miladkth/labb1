package bo.handlers;

import bo.entities.Product;
import db.exceptions.DbException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ProductService{
    public static void addItem (Product newItem) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();
            db.productDb.insertSingle(newItem);
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }

    }
    public static Collection<Product> getItems () throws DbException{
        DbHandler db = null;
        Collection<Product> items;
        try{
            db = new DbHandler();
            items = db.productDb.getAll();
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(db != null)
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
