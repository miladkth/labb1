package bo.handlers;

import bo.entities.Product;
import bo.entities.User;
import db.exceptions.DbException;
import ui.DTOs.ProductDTO;
import ui.DTOs.UserDTO;

import java.util.ArrayList;
import java.util.Collection;

public class UserService {

    public static void createUser(User user) throws DbException {
        DbHandler db = null;
        try {
             db = new DbHandler();

            db.userDb.insertSingle(user);
        } catch (DbException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            if(db!=null)
                db.release();
        }
    }

    public static UserDTO getUserInfo(String id) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();

            Collection<User> users = db.userDb.getUserById(id);
            User user = users.iterator().next();
            return new UserDTO(user);
        } catch (DbException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new DbException(e.getMessage());
        }finally {
            if(db!=null)
                db.release();
        }
    }

    public static void updateUserRole(String id, String newRole) throws BoException, DbException {
        if(!newRole.equals("customer") && !newRole.equals("admin") && !newRole.equals("warehouse")){
            throw new BoException("Unrecognized role value");
        }
        DbHandler db = null;
        try {
            db = new DbHandler();
            db.userDb.updateRole(id, newRole);
        } catch (DbException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }

    public static void blockUser(String id, boolean isActive) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();
            db.userDb.setBlockUser(id, isActive);
        } catch (DbException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }

    public static Collection<UserDTO> getAllUsers() throws DbException {
        DbHandler db = null;
        Collection<UserDTO> productsDto = new ArrayList<>();
        try{
            db = new DbHandler();
            Collection<User> items = db.userDb.getAll();

            items.forEach(i-> productsDto.add(new UserDTO(i)));
        }catch (DbException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(db != null)
                db.release();
        }
        return productsDto;
    }

    public static UserDTO createUserWithEmailAndPassword(String name, String email, String password, String role) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();
            User user = new User(name,password,role,email);
            db.userDb.insertSingle(user);
            return new UserDTO(user);
        } catch (DbException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }

    public static UserDTO logInUserWithEmailAndPassword(String email, String password) throws DbException {

        DbHandler db = null;
        try {
            db = new DbHandler();
            User user = db.userDb.getUserByEmail(email);

            if (user==null)
                return null;

            if (!user.getIsActive())
                return null;

            if (!password.equals(user.getPassword()))
                return null;

            return new UserDTO(user);
        } catch (DbException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(db != null)
                db.release();
        }
    }
}
