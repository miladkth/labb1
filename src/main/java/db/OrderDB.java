package db;

import bo.entities.Order;
import bo.entities.Product;
import db.exceptions.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class OrderDB {
    private Connection conn;

    protected OrderDB(Connection conn) {
        this.conn = conn;
    }

    public Collection<Order> getAll() throws DbException {
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("select * from t_orders as o join t_order_product on t_order_product.order_id = o.id join t_products on t_products.id = t_order_product.product_id order by o.id");
            return mapResultSet(rs);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    private static Collection mapResultSet(ResultSet rs) throws SQLException {
        Vector<Order> v = new Vector<>();
        String id = "";

        Order o = null;

        while (rs.next()) {
            String idtemp = rs.getString("order_id");
            if(!id.equals(idtemp)){
                if(o!=null)
                    v.add(o);
                o = new Order();
            }

            id = idtemp;
            o.setId(rs.getString("order_id"));
            o.setUserId(rs.getString("user_id"));
            o.setFullFilled(rs.getBoolean("fullfilled"));
            o.setShippingAddress(rs.getString("shippingAddress"));

            String product_id = rs.getString("product_id");
            int qty = rs.getInt(8);
            String title = rs.getString("title");
            String descr = rs.getString("description");
            float price = rs.getFloat("price");
            String img = rs.getString("imageUrl");

            Product p = new Product(product_id, title, descr, qty, price, img);
            o.addProduct(p);
        }
        v.add(o);
        return v;
    }

    public int[] insertSingle(Order order) throws DbException {
        Boolean t = true;
        try {
            t = this.conn.getAutoCommit();

            if(t){
                this.conn.setAutoCommit(false);
            }

            PreparedStatement pstm = this.conn.prepareStatement("insert into t_orders (id, user_id, fullfilled, shippingAddress) values (?,?,?,?)");
            pstm.setString(1, order.getId());
            pstm.setString(2, order.getUserId());
            pstm.setBoolean(3, order.isFullFilled());
            pstm.setString(4, order.getShippingAddress());
            pstm.executeUpdate();


            PreparedStatement pstm1 = this.conn.prepareStatement("insert into t_order_product (order_id, product_id, quantity) values (?,?,?)");
            ArrayList<Product> products  = order.getProducts();
            for (Product p : products) {
                pstm1.setString(1,order.getId());
                pstm1.setString(2,p.getId());
                pstm1.setInt(3,p.getQuantity());
                pstm1.addBatch();
            }
            int[] result = pstm1.executeBatch();

            if(t){
                this.conn.setAutoCommit(true);
            }
                return result;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if(!t){
                    this.conn.rollback();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
                throw new DbException(e.getMessage() + ", " + ex.getMessage());
            }
            throw new DbException(e.getMessage());
        }
    }

}
