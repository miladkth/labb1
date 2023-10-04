package db;

import db.exceptions.DbException;
import java.sql.Connection;
import java.sql.SQLException;

public class DbContext{
    protected Connection con;
    public ProductDB productDb;
    public DbContext() throws DbException {
        try {
            this.con = DbManager.getConnection();
        } catch (SQLException e){
            throw new DbException("CAN NOT GET CONNECTION: " + e.getMessage());
        }
        this.productDb = new ProductDB(con);
    }
    public void startTransaction() throws DbException {
        try{
            this.con.setAutoCommit(false);
            this.con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        }catch (SQLException e){
            throw new DbException("CAN NOT START START A TRANSACTION: " + e.getMessage());
        }
    }
    public void commitTransaction() throws DbException{
        try {
            this.con.commit();
        }catch (SQLException e){
            throw new DbException("CAN NOT COMMIT TRANSACTION: " + e.getMessage());
        }finally {
            try {
                this.con.setAutoCommit(true);
            }catch (SQLException e){
                throw new DbException("CAN NOT SET AUTO COMMIT TO TRUE: " + e.getMessage());
            }
        }
    }
    public void rollbackTransaction() throws DbException{
        try{
            this.con.rollback();
        } catch (SQLException e){
            throw new DbException("CAN NOT ROLLBACK TRANSACTION: " + e.getMessage());
        }finally {
            try {
                this.con.setAutoCommit(true);
            }catch (SQLException e){
                throw new DbException("CAN NOT SET AUTO COMMIT TO TRUE: " + e.getMessage());
            }
        }
    }
    public void endTransaction() throws DbException {
        try {
            this.con.setAutoCommit(true);
        }catch (SQLException e){
            throw new DbException("CAN NOT SET AUTO SET AUTO COMMIT TO FALSE: " + e.getMessage());
        }
    }
    public void release(){
        DbManager.releaseConnection(this.con);
    }
}
