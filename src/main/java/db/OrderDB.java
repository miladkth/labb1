package db;

import bo.entities.Order;
import bo.entities.Product;
import db.exceptions.DbException;

import java.sql.*;
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
            ResultSet rs = st.executeQuery("SELECT * from t_orders");
            return mapResultSet(rs);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private static Collection mapResultSet(ResultSet rs) throws SQLException {
        Vector<Order> v = new Vector<>();
        while (rs.next()) {
            String id = rs.getString("id");
            String userId = rs.getString("user_id");
            boolean fullFilled = rs.getBoolean("fullfilled");
            String shippingAddress = rs.getString("shippingAddress");

            Order item = new Order(id, userId, fullFilled, shippingAddress);
            v.add(item);
        }
        return v;
    }

    public int[] insertSingle(Order order, List<Product> products) throws DbException {
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
