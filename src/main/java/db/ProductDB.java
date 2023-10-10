package db;

import bo.entities.Product;
import db.exceptions.DbException;

import java.sql.*;
import java.util.*;

public class ProductDB{
    private Connection conn;
    protected ProductDB(Connection conn){
        this.conn = conn;
    }

    public Product getById(String id) throws DbException {
        try{
            PreparedStatement pstm = this.conn.prepareStatement("select * from t_products as p join t_caterogies on p.id = t_caterogies.product_id and p.id = ? order by t_caterogies.product_id");
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            Collection<Product> products = mapResultSet(rs, true);
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

    public void updateIsShown(String id, boolean newVal) throws DbException {
        try{
            PreparedStatement pstm = this.conn.prepareStatement("update t_products set isShown = ? where id = ?");
            pstm.setBoolean(1, newVal);
            pstm.setString(2, id);

            pstm.executeUpdate();
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
            return mapResultSet(rs, true);
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    public Collection<Product> getAllListed() throws DbException{
        try{
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from t_products as p join t_caterogies on p.id = t_caterogies.product_id && isShown=true");
            return mapResultSet(rs, true);
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    public Collection<Product> getByCategory(String category) throws DbException {
        try{
            PreparedStatement st = this.conn.prepareStatement("select * from t_products as p join t_caterogies on p.id = t_caterogies.product_id and t_caterogies.category= ?;");
            st.setString(1, category);
            ResultSet rs = st.executeQuery();
            return mapResultSet(rs, true);
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    public Collection<Product> getListedByCategory(String category) throws DbException {
        try{
            PreparedStatement st = this.conn.prepareStatement("select * from t_products as p join t_caterogies on p.id = t_caterogies.product_id and t_caterogies.category= ? and p.isShown = true;");
            st.setString(1, category);
            ResultSet rs = st.executeQuery();
            return mapResultSet(rs, true);
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    public Collection<String> getAllCategories() throws DbException {
        try{
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("select category from t_caterogies group by category;");
            ArrayList<String> categories = new ArrayList<>();
            while (rs.next()){
                categories.add(rs.getString(1));
            }
            return categories;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    public Collection<Product> getManyById(List<String> ids) throws DbException{
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
        String queryStr = String.format("SELECT * FROM t_products WHERE id IN (%s)", inSql);

        System.out.println(inSql);
        System.out.println(queryStr);
        try{
            PreparedStatement pstm = this.conn.prepareStatement(queryStr);
            for (int i = 0; i < ids.size(); i++) {
                pstm.setString(i+1, ids.get(i));
            }
            ResultSet rs = pstm.executeQuery();
            return mapResultSet(rs,false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int[] insertSingle(Product product) throws DbException {
        boolean t = true;

        try {
            t = this.conn.getAutoCommit();
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
                if (t  && product.getCategories().size()>0) {
                    this.conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DbException(e.getMessage() + "," + ex.getMessage());
            }
                throw new DbException(e.getMessage());
        }
    }
    private static Collection<Product> mapResultSet( ResultSet rs , boolean readCategory) throws SQLException {
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
            p.setIsShown(rs.getBoolean("isShown"));
            if(readCategory){
                p.addCategory(rs.getString("category"));
            }
        }
        v.add(p);
        return v;
    }
}