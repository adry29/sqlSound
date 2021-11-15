
package dao;

import utils.bdConnector;
import com.mycompany.sqlsound.models.Artist;
import com.mycompany.sqlsound.models.Disk;
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
public class ArtistDAO{
    
    bdConnector connector = new bdConnector();
    Connection conn;
    Statement stmt;

    public ArtistDAO() throws SQLException {
        this.conn = connector.connect();
        stmt = conn.createStatement();
    }
    
    public void insertArtist(Artist a){
        
        try{
            stmt.executeUpdate("insert into artista(nombre,nacionalidad,foto) values ('"
                               +a.getName()+"', '"+a.getNationality()+"', '"+a.getPhoto()+"');"
            );
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public Artist getArtistbyId(int id){
        Artist result = new Artist();
        ResultSet rs;
        String query = "select * from artista where artista.id ="+id+";";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                result = new Artist(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Artist> getAllArtists(){
        ResultSet artist;
        Artist a;
        ArrayList<Artist> result = new ArrayList<>();
        String query = "select * from artista;";
        try{
            artist = stmt.executeQuery(query);
            while(artist.next()){
            a = new Artist(artist.getInt(1),artist.getString(2), artist.getString(3), artist.getString(4));
            result.add(a);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return result;
        
    }
    
    public ArrayList<Artist> getArtistsbyName(String name){
        ResultSet artist;
        Artist a;
        ArrayList<Artist> result = new ArrayList<>();
        String query = "select * from artista;";
        try{
            artist = stmt.executeQuery(query);
            while(artist.next()){
            if(artist.getString(2).contains(name)){
                a = new Artist(artist.getInt(1),artist.getString(2), artist.getString(3), artist.getString(4));
                
                result.add(a);
            }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return result;
        
    }
    
    public void updateArtist(Artist a, int id){
        String query = "update artista set artista.nombre='"+a.getName()+
                       "', artista.nacionalidad='"+a.getNationality()+
                       "', artista.foto='"+a.getPhoto()+
                       "' where artista.id="+id+";";
        try{
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public void deleteArtist(int id){
        String query = "delete from artista where id="+id+";";
        try{
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
