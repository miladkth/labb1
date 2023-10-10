package bo.handlers;

import bo.entities.Product;
import db.exceptions.DbException;
import ui.DTOs.ProductDTO;

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

    public static void toggleCategory(String id, String category) throws DbException{
        DbHandler db = null;
        try {
            db = new DbHandler();
            Product p = db.productDb.getById(id);
            if(p == null)
                return;

            for(String cat:p.getCategories()){
                if(cat.equals(category)){
                    db.productDb.removeCategory(id, category);
                    return;
                }
            }
            db.productDb.addCategory(id, category);
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }
    public static void setIsShown(String id, boolean isShown) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();
            db.productDb.updateIsShown(id, isShown);
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }
    public static void updateField(String id, String newDescription, String fieldname) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();
            db.productDb.updateOneField(id, newDescription, fieldname);
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }
    public static Collection<ProductDTO> getByCategory(String category, boolean includeNotShown) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();
            Collection<Product> products;
            if(includeNotShown){
                products = db.productDb.getByCategory(category);
            }else {
                products = db.productDb.getListedByCategory(category);
            }
            Collection<ProductDTO> productDTOS = new ArrayList<>();
            products.forEach(p -> {
                productDTOS.add(new ProductDTO(p));
            });
            return productDTOS;
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }
    public static Collection<String> getAllCategories() throws DbException {
        DbHandler db = null;
        try{
            db = new DbHandler();
            return db.productDb.getAllCategories();
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(db != null)
                db.release();
        }
    }
    public static Collection<ProductDTO> getAll(boolean includeNotListed) throws DbException{
        DbHandler db = null;
        Collection<ProductDTO> productsDto = new ArrayList<>();
        try{
            db = new DbHandler();
            Collection<Product> items;
            if(includeNotListed){
                items = db.productDb.getAll();
            }else{
                items  = db.productDb.getAllListed();
            }
            items.forEach(i-> productsDto.add(new ProductDTO(i)));
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(db != null)
                db.release();
        }
        return productsDto;
    }

    public static ArrayList<String> categoriesFromString(String categoryString){
            String[] result = categoryString.split(",");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(result));
            ArrayList<String> slist = new ArrayList<>();
            list.forEach(l -> slist.add(l.trim()));
            return slist;
    }

    public static ProductDTO getById(String id) throws DbException {
        DbHandler db = null;
        try{
            db = new DbHandler();
            Product product = db.productDb.getById(id);
            if(product == null)
                return null;
            return new ProductDTO(product);
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(db != null)
                db.release();
        }
    }
}
