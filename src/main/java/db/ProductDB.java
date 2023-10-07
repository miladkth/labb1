package db;

import bo.entities.Product;
import db.exceptions.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class ProductDB{
    private Connection conn;
    protected ProductDB(Connection conn){
        this.conn = conn;
    }

    public Product getById(String id) throws DbException {
        try{
            Statement st = this.conn.createStatement();
            PreparedStatement pstm = this.conn.prepareStatement("select * from t_products as p join t_caterogies on p.id = t_caterogies.product_id and p.id = ? order by t_caterogies.product_id");
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            Collection<Product> products = mapResultSet(rs);
            System.out.println(products.size());
            if (products.size()>0) {
                return products.iterator().next();
            }
            return null;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    public void removeCategory(String id, String category){
        try{
            PreparedStatement pstm1 = this.conn.prepareStatement("delete from t_caterogies where product_id = ? and category = ?");
            pstm1.setString(1, id);
            pstm1.setString(2, category);
            pstm1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addCategory(String id, String category){
        try{
            PreparedStatement pstm1 = this.conn.prepareStatement("insert into t_caterogies (product_id, category) value (?,?)");
            pstm1.setString(1, id);
            pstm1.setString(2, category);
            pstm1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOneField(String id, String newVal, String fieldName) throws DbException {
        String sql = null;
        switch (fieldName){
            case "title":
                sql = "update t_products set title = ? where id = ?";
                break;
            case "description":
                sql = "update t_products set description = ? where id = ?";
                break;
            case "price":
                sql = "update t_products set price = ? where id = ?";
                break;
            case "quantity":
                sql = "update t_products set quantity = ? where id = ?";
                break;
            case "imageUrl":
                sql = "update t_products set imageUrl = ? where id = ?";
                break;
            default:
                throw new DbException("No field name " + fieldName);
        }

        try{
            PreparedStatement pstm = this.conn.prepareStatement(sql);

            if(fieldName.equals("price")){
                float val = Float.parseFloat(newVal);
                pstm.setFloat(1, val);
            }else if(fieldName.equals("quantity")){
                int val = Integer.parseInt(newVal);
                pstm.setInt(1, val);
            }else{
                pstm.setString(1, newVal);
            }

            pstm.setString(2, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Collection<Product> getAll() throws DbException{
        try{
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from t_products as p join t_caterogies on p.id = t_caterogies.product_id");
            return mapResultSet(rs);
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    public int[] insertSingle(Product product) throws DbException {
        boolean t = true;

        try {
            if(t && product.getCategories().size()>0) {
                this.conn.setAutoCommit(false);
            }
            PreparedStatement pstm = this.conn.prepareStatement("insert into t_products (id, title, description, quantity, price, imageUrl) values (?,?,?,?,?,?)");
            pstm.setString(1, product.getId());
            pstm.setString(2,product.getTitle());
            pstm.setString(3, product.getDescription());
            pstm.setInt(4, product.getQuantity());
            pstm.setFloat(5, product.getPrice());
            pstm.setString(6, product.getImgUrl());
            pstm.executeUpdate();

            PreparedStatement pstm1 = this.conn.prepareStatement("insert into t_caterogies (product_id, category) value (?,?)");
            for (String c : product.getCategories()) {
                pstm1.setString(1,product.getId());
                pstm1.setString(2,c);
                pstm1.addBatch();
            }

            int[] result = pstm1.executeBatch();

            if (t && product.getCategories().size()>0) {
                this.conn.setAutoCommit(true);
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
            try {
                if (!t  && product.getCategories().size()>0) {
                    this.conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DbException(e.getMessage() + "," + ex.getMessage());
            }
                throw new DbException(e.getMessage());
        }
    }
    private static Collection<Product> mapResultSet( ResultSet rs ) throws SQLException {
        Vector<Product> v = new Vector<>();
        String id = "";

        Product p = null;

        while(rs.next()){
            String idtemp = rs.getString("id");
            if(!id.equals(idtemp)){
                if(p!=null)
                    v.add(p);
                p = new Product();
            }
            id = idtemp;
            p.setId(idtemp);
            p.setTitle(rs.getString("title"));
            p.setDescription(rs.getString("description"));
            p.setQuantity(rs.getInt("quantity"));
            p.setPrice(rs.getFloat("price"));
            p.setImgUrl(rs.getString("imageUrl"));
            p.addCategory(rs.getString("category"));
        }
        v.add(p);
        return v;
    }
}