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

    public static void getUserInfo(String id) throws DbException {
        try {
            DbHandler db = new DbHandler();

            Collection<User> users = db.userDb.getUserById(id);
            System.out.println(users.iterator().next().toString());
        } catch (DbException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
