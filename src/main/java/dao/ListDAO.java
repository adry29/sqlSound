
package dao;

import utils.bdConnector;
import com.mycompany.sqlsound.models.List;
import com.mycompany.sqlsound.models.Song;
import com.mycompany.sqlsound.models.User;
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
public class ListDAO {
    
    List l;
    User u;
    bdConnector connector = new bdConnector();
    Connection conn;
    Statement stmt;
    
    public ListDAO() throws SQLException{
        this.conn = connector.connect();
        this.stmt = conn.createStatement();
    }
    
    public void insertList(List l){
        try{
            String query = "insert into lista(nombre, descripcion, idcreador) values ('"
                    + l.getName()+"','"
                    + l.getDescription()+"','"
                    + l.getUser_id()+"');";
            
            stmt.executeUpdate(query);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateList(List l, int id){
        try{
            String query = "update lista set lista.nombre='"
                    + l.getName()+"',lista.descripcion='"
                    + l.getDescription()+"',lista.idcreador="
                    + l.getUser_id()+";";
            
            stmt.executeUpdate(query);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void deleteList(int id){
        try{
            String query = "delete from lista where lista.id="+id+";";
            
            stmt.executeUpdate(query);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void addSongtoList(int song_id, int list_id){
        try {
            String query = "insert into cancion_lista(idcancion, idlista) values('"
                    + song_id + "','"
                    + list_id + "');";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Song> getSongsfromList(int list_id){

        ArrayList<Song> result = new ArrayList();
        ResultSet rs;
        String query = "select * from cancion join lista join cancion_lista where cancion.id=cancion_lista.idcancion and lista.id=cancion_lista.idlista and lista.id=" + list_id + ";";
        try {
            Song s;
            
            
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                s = new Song(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                result.add(s);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
        
        return result;
    }
    
    public void deleteSongfromList(int song_id, int list_id){
        try{
            String query = "delete from cancion_lista where cancion_lista.idcancion="+song_id+" and cancion_lista.idlista="+list_id+";";
            
            stmt.executeUpdate(query);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<List> getAllLists(){
        ArrayList<List> result = new ArrayList();
        ResultSet rs;
        try{
            String query = "select * from lista";
            
            rs = stmt.executeQuery(query);
            while(rs.next()){
                l = new List(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                
                result.add(l);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public List getListbyId(int id){
        l = new List();
        ResultSet rs;
        try{
            String query = "select * from lista where lista.id="+id+";";
            
            rs = stmt.executeQuery(query);
            while(rs.next()){
                l.setId(rs.getInt(1));
                l.setName(rs.getString(2));
                l.setDescription(rs.getString(3));
                l.setUser_id(rs.getInt(4));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return l;
    }
    
    public void suscribetoList(int id_user, int id_list){
        try{
            
            String query = "insert into suscripcion(idusuario, idlista) values ("+id_user+","+id_list+");";
            stmt.executeUpdate(query);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void unsuscribetoList(int id_user, int id_list){
        try{
            
            String query = "delete from suscripcion where suscripcion.idusuario="+id_user+" and suscripcion.idlista="+id_list+";";
            stmt.executeUpdate(query);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<User> getSuscribers(int list_id){
        ArrayList<User> result = new ArrayList();
        try{
            ResultSet rs;
            String query = "select usuario.id, usuario.nickname from usuario join lista join suscripcion where usuario.id=suscripcion.idusuario and lista.id=suscripcion.idlista and lista.id="+list_id+";";
            
            rs = stmt.executeQuery(query);
            while(rs.next()){
                u = new User(rs.getInt(1), rs.getString(2), "");
                result.add(u);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<List> getsuscribedLists(int user_id){
        ArrayList<List> result = new ArrayList();
        ResultSet rs;
        String query = "select * from lista join usuario join suscripcion where lista.id = suscripcion.idlista and usuario.id = suscripcion.idusuario and usuario.id = "+user_id+";";
        try{
            
            rs = stmt.executeQuery(query);
            while(rs.next()){
                l = new List(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                
                result.add(l);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<List> getListsbyUser(User u){
        ArrayList<List> result = new ArrayList();
        for(List l : getAllLists()){
            if(l.getUser_id() == u.getId()){
                result.add(l);
            }
        }
        return result;
    }
    
    public ArrayList<List> getListsbyName(String name){
        ArrayList<List> result = new ArrayList();
        ResultSet rs;
        String query = "select * from lista where lista.nombre like '%"+name+"%';";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                l = new List(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                result.add(l);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}


