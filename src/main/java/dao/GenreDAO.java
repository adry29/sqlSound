
package dao;

import utils.bdConnector;
import com.mycompany.sqlsound.models.Genre;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author adryc
 */
public class GenreDAO {

    bdConnector connector = new bdConnector();
    Connection conn;
    Statement stmt;
    Genre g;
    
    public GenreDAO() throws SQLException{
        this.conn = connector.connect();
        this.stmt = conn.createStatement();
    }

    public void insertGenre(Genre g){
        String query = "insert into genero(nombre) values ('" + g.getName() + "');";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Genre> getAllGenres(){
        ArrayList<Genre> result = new ArrayList();
        ResultSet rs;
        String query = "select * from genero;";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                g = new Genre(rs.getString(2));
                g.setId(Integer.parseInt(rs.getString(1)));
                result.add(g);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public Genre getGenrebyId(int id){
        g = new Genre();
        ResultSet rs;
        String query = "select * from genero where id="+id+";";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                g = new Genre(rs.getString(2));
                g.setId(Integer.parseInt(rs.getString(1)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return g;
    }
    
    public void updateGenre(Genre g2, int id){
        String query = "update genero set genero.nombre='"+g2.getName()+
                "' where genero.id="+id+";";
        try{
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public void deleteGenre(int id){
        String query = "delete from genero where genero.id="+id+";";
        try{
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}
