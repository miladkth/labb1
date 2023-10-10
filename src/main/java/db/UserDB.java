package db;

import bo.entities.User;
import db.exceptions.DbException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserDB {
    private Connection conn;
    protected UserDB(Connection conn){
        this.conn = conn;
    }

    public Collection<User> getAll() throws DbException {
        try{
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from t_users");

            return mapResultSet(rs);
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public void updateRole(String id, String newRole) throws DbException {
        try {
            PreparedStatement pstm = this.conn.prepareStatement("update t_users set role = ? where id = ?");
            pstm.setString(1, newRole);
            pstm.setString(2, id);
            pstm.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public void setBlockUser(String id, boolean isActive) throws DbException {
        try {
            PreparedStatement pstm = this.conn.prepareStatement("update t_users set isActive = ? where id = ?");
            pstm.setBoolean(1, isActive);
            pstm.setString(2, id);
            pstm.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public Collection<User> getUserById(String id) throws DbException {
        try {
            PreparedStatement st = this.conn.prepareStatement("SELECT * from t_users WHERE id = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            return mapResultSet(rs);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public User getUserByEmail(String email) throws DbException {
        try {
            PreparedStatement st = this.conn.prepareStatement("SELECT * from t_users WHERE email = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            Collection<User> user = mapResultSet(rs);
            if (user.size() == 0){
                return null;
            }
            return user.iterator().next();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public int insertSingle(User user) throws DbException {
        try {
            PreparedStatement pstm = this.conn.prepareStatement("insert into t_users (id,username, email, password, role) values (?,?,?,?,?)");
            pstm.setString(1, user.getId());
            pstm.setString(2,user.getUserName());
            pstm.setString(3, user.getEmail());
            pstm.setString(4, user.getPassword());
            pstm.setString(5, user.getRole());
            return pstm.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    private Collection<User> mapResultSet(ResultSet rs) throws SQLException {

        Collection<User> users = new ArrayList<>();

        while (rs.next()) {
            String id = rs.getString("id");
            String userName = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            boolean isActive = rs.getBoolean("isActive");

            User user = new User(id,userName,password,role,email, isActive);
            users.add(user);
        }
        return users;
    }
}
