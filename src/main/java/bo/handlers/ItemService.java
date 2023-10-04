package bo.handlers;

import bo.entities.Item;
import db.DbContext;
import db.exceptions.DbException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class ItemService {
    public static void addItem (Item newItem) throws DbException {

    }
    public static Collection<Item> getItems () throws DbException{
        DbContext db = new DbContext();
        Collection<Item> items;
        try{
            String id = UUID.randomUUID().toString();
            db.startTransaction();
            db.itemDb.insertSingle(new Item("new","name1"));
            db.itemDb.insertSingle(new Item(id, "new","name1"));
            db.itemDb.insertSingle(new Item(id,"new","name1"));
            items = db.itemDb.getAll();
            db.commitTransaction();
            System.out.println("transaction completed");
        }catch (DbException e){
            System.out.println("transaction rolled back");
            db.rollbackTransaction();
            throw e;
        } finally {
            db.endTransaction();
            db.release();
            System.out.println("connection release");
        }
        return items;
    }
}
