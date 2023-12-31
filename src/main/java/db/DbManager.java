package db;

import db.exceptions.DbException;
import db.exceptions.NoConnectionException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

class DbManager {
    private static DbManager instance;
    private ArrayList<Connection> connections;

    private DbManager(){
        try{
            connections = new ArrayList<>();
            String username = System.getenv("DB_USER_NAME");
            String pwd = System.getenv("DB_PASSWORD");
            String url = System.getenv("DB_URL_STRING");
            String db_name = "webshop";

            int count = 5;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            for (int i = 0; i < count; i++) {
                Connection con = DriverManager.getConnection(url+db_name, username, pwd);
                connections.add(con);
            }

            System.out.println("Connected to database");
            System.out.println(count + " connections created");
        } catch (Exception e) {
            System.out.println("Cannot get DB connection ---------------------");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    protected static DbManager getInstance(){
        System.out.println("get instance");
        if(instance == null){
            System.out.println("instance is null");
            instance = new DbManager();
        }
        return instance;
    }
    protected static Connection getConnection() throws SQLException, DbException {
        DbManager db = getInstance();
        if(db.connections.size() > 0){
            return db.connections.remove(0);
        }
        throw new DbException("Connection pool empty");
    }
    protected static void releaseConnection(Connection con){
        getInstance().connections.add(con);
    }
}
