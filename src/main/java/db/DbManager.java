package db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DbManager {
    private static DbManager instance;
    private Connection con;

    private DbManager(){
        try{
            String dbUrl = "jdbc:mysql://shop-lab.cp4uwsoxtikt.eu-north-1.rds.amazonaws.com:3306/test";
            String username = "admin";
            String password = "123456789";

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connected to database");

        } catch (Exception e) {
            System.out.println("Cannot get DB connection ---------------------");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static DbManager getInstance(){
        if(instance == null)
            instance = new DbManager();
        return instance;
    }

    public static Connection getConnection() {
        return getInstance().con;
    }

}
