package db;

import db.exceptions.NoConnectionException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

class DbManager {
    private static DbManager instance;
    private ArrayList<Connection> connections;
    private String dbUrl = "jdbc:mysql://shop-lab.cp4uwsoxtikt.eu-north-1.rds.amazonaws.com:3306/webshop";
    private String username = "admin";
    private String password = "123456789";

    private DbManager(){
        try{
            connections = new ArrayList<>();

            int count = 5;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            for (int i = 0; i < count; i++) {
                Connection con = DriverManager.getConnection(this.dbUrl, this.username, this.password);
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
    protected static Connection getConnection() throws SQLException {
        DbManager db = getInstance();
        if(db.connections.size() > 0){
            return db.connections.remove(0);
        }

        return db.connections.remove(0);
    }
    protected static void releaseConnection(Connection con){
        getInstance().connections.add(con);
    }
}
