
package dao;

import utils.bdConnector;
import com.mycompany.sqlsound.models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adryc
 */
public class UserDAO {
    
    User u;
    bdConnector connector = new bdConnector();
    Connection conn;
    Statement stmt;

    public UserDAO() throws SQLException {
        this.conn = connector.connect();
        stmt = conn.createStatement();
    }
    
    
    public void insertUser(User u){
        try {
            String query = "insert into usuario(nickname, password) values ('"+u.getNickname()
                    + "','"
                    + u.getPassword()+"');";
            
            stmt.executeUpdate(query);
            
        }catch(SQLException e){
        e.printStackTrace();
        }
        
    }
    
    public void updateUser(User u, int id){
        try {
            String query = "update usuario set usuario.nickname='"+u.getNickname()
                    + "',usuario.password='"
                    + u.getPassword()+"' where usuario.id="+id+";";
            stmt.executeUpdate(query);
        }catch(SQLException e){
        e.printStackTrace();
        }
        
    }
    
    public void deleteUser(int id){
        try {
            String query = "delete from usuario where usuario.id="+id+";";
            Statement stmt = conn.createStatement();
        }catch(SQLException e){
        e.printStackTrace();
        }
        
    }
    
    public User getUserbyId(int id){
        try{
            ResultSet rs;
            String query = "select * from usuario where usuario.id="+id+";";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                u = new User(rs.getString(2), rs.getString(3));
                u.setId(Integer.parseInt(rs.getString(1)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return u;
    }
    
    public ArrayList<User> getAllUsers(){
        ArrayList<User> result = new ArrayList();
        try{
            ResultSet rs;
            String query = "select * from usuario;";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                u = new User(rs.getString(2), rs.getString(3));
                u.setId(rs.getInt(1));
                result.add(u);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
