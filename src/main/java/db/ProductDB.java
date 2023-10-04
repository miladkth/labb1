package db;

import bo.entities.Product;
import db.exceptions.DbException;

import java.sql.*;
import java.util.Collection;
import java.util.Vector;

public class ProductDB{
    private Connection conn;
    protected ProductDB(Connection conn){
        this.conn = conn;
    }
    public Collection<Product> getAll() throws DbException{
        try{
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from t_products");
            return mapResultSet(rs);
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    public int insertSingle(Product product) throws DbException {
        try {
            PreparedStatement pstm = this.conn.prepareStatement("insert into t_products (id, title, description, quantity, price, imageUrl) values (?,?,?,?,?,?)");
            pstm.setString(1, product.getId());
            pstm.setString(2,product.getTitle());
            pstm.setString(3, product.getDescription());
            pstm.setInt(4, product.getQuantity());
            pstm.setFloat(5, product.getPrice());
            pstm.setString(6, product.getImgUrl());
            return pstm.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    private static Collection mapResultSet( ResultSet rs ) throws SQLException {
        Vector<Product> v = new Vector<>();
        while(rs.next()){
            String id = rs.getString("id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            int quantity = rs.getInt("quantity");
            float price = rs.getFloat("price");
            String imageUrl = rs.getString("imageUrl");


            Product item = new Product(id, title, description, quantity, price, imageUrl);
            v.add(item);
        }
        return v;
    }
}