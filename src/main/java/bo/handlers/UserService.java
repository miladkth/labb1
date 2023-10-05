package bo.handlers;

import bo.entities.User;
import db.exceptions.DbException;
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

    public static User getUserInfo(String id) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();

            Collection<User> users = db.userDb.getUserById(id);
            return users.iterator().next();
        } catch (DbException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new DbException(e.getMessage());
        }finally {
            if(db!=null)
                db.release();
        }
    }

    public static User createUserWithEmailAndPassword(String name, String email, String password) throws DbException {
        DbHandler db = null;
        try {
            db = new DbHandler();
            User user = new User(name,password,email);
            db.userDb.insertSingle(user);
            return user;
        } catch (DbException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(db!=null)
                db.release();
        }
    }

    public static User logInUserWithEmailAndPassword(String email, String password) throws DbException {

        DbHandler db = null;
        try {
            db = new DbHandler();
            User user = db.userDb.getUserByEmail(email);

            if (user==null)
                return null;

            if (!password.equals(user.getPassword()))
                return null;

            return user;
        } catch (DbException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(db != null)
                db.release();
        }
    }
}
