package bo.handlers;

import bo.entities.Product;
import db.DbContext;
import db.exceptions.DbException;

import java.util.Collection;

public class ItemService {
    public static void addItem (Product newItem) throws DbException {

    }
    public static Collection<Product> getItems () throws DbException{
        DbContext db = new DbContext();
        Collection<Product> items;
        try{
            items = db.itemDb.getAll();
        }catch (DbException e){
            throw e;
        } finally {
            db.release();
        }
        return items;
    }
}
