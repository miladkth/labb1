package db;

import bo.entities.Item;
import db.exceptions.DbException;

import java.sql.*;
import java.util.Collection;
import java.util.Vector;

public class ProductDB{
    private Connection conn;
    protected ProductDB(Connection conn){
        this.conn = conn;
    }
    public Collection<Item> getAll() throws DbException{
        try{
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from items");
            return mapResultSet(rs);
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    public int insertSingle(Item item) throws DbException {
        try {
            PreparedStatement pstm = this.conn.prepareStatement("insert into items (id, name, title) values (?,?,?)");
            pstm.setString(1, item.getId());
            pstm.setString(2, item.getName());
            pstm.setString(3,item.getTitle());
            return pstm.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    private static Collection mapResultSet( ResultSet rs ) throws SQLException {
        Vector<Item> v = new Vector<>();
        while(rs.next()){
            String id = rs.getString("id");
            String name = rs.getString("name");
            String title = rs.getString("title");
            Item item = new Item(id, name, title);
            v.add(item);
        }
        return v;
    }
}