package db;

import bo.entities.Order;
import bo.entities.Product;
import db.exceptions.DbException;

import java.sql.*;
import java.util.Collection;
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

    public int insertSingle(Order order) throws DbException {
        try {
            PreparedStatement pstm = this.conn.prepareStatement("insert into t_orders (id, user_id, fullfilled, shippingAddress) values (?,?,?,?)");
            pstm.setString(1, order.getId());
            pstm.setString(2, order.getUserId());
            pstm.setBoolean(3, order.isFullFilled());
            pstm.setString(4, order.getShippingAddress());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

}
