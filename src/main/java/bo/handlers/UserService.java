package bo.handlers;

import bo.entities.User;
import db.exceptions.DbException;

import java.util.Collection;

public class UserService {

    public static void createUser(User user) throws DbException {
        try {
            DbHandler db = new DbHandler();

            db.userDb.insertSingle(user);
        } catch (DbException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static User getUserInfo(String id) throws DbException {
        try {
            DbHandler db = new DbHandler();

            Collection<User> users = db.userDb.getUserById(id);
            return users.iterator().next();
        } catch (DbException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new DbException(e.getMessage());
        }
    }
}
