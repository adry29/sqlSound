
package dao;

import utils.bdConnector;
import com.mycompany.sqlsound.models.Song;
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
public class SongDAO {
    Song s;
    bdConnector connector = new bdConnector();
    Connection conn;
    Statement stmt;
    
    public SongDAO() throws SQLException{
        this.conn = connector.connect();
        this.stmt = conn.createStatement();
    }
    
    public void insertSong(Song s){
        String query = "insert into cancion(nombre, duracion, iddisco, idgenero) values('"+
                s.getName()+"',"+s.getDuration()+","+s.getDiskid()+","+s.getGenreId()+");";
        try{
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public void updateSong(Song s, int id){
        String query = "update cancion set nombre='"+s.getName()+"',duracion='"+s.getDuration()+"',iddisco='"+s.getDiskid()+"',idgenero='"+s.getGenreId()+"' where cancion.id="+id+";";
        try{
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public void deleteSong(int id){
        String query = "delete from cancion where id="+id+";";
        try{
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Song> getAllSongs(){
        ArrayList<Song> result = new ArrayList();
        ResultSet rs;
        String query = "select * from cancion";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString(5)!= null){
                    s = new Song(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getInt(5));
                    
                    result.add(s);
                }else{
                    s = new Song(rs.getString(2), Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)));
                    s.setId(rs.getInt(1));
                    result.add(s);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public Song getSongbyId(int id){
        ResultSet rs;
        s = new Song();
        String query = "select * from cancion where id="+id+";";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString(5)!= null){
                    s.setId(rs.getInt(1));
                    s.setName(rs.getString(2));
                    s.setDuration(rs.getInt(3));
                    s.setDiskid(rs.getInt(4));
                    s.setGenreId(5);
                    
                    
                }else{
                    s.setId(rs.getInt(1));
                    s.setName(rs.getString(2));
                    s.setDuration(rs.getInt(3));
                    s.setDiskid(rs.getInt(4));
                    
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return s;
    }
    
    public ArrayList<Song> getSongsbyName(String name){
        ArrayList<Song> result = new ArrayList();
        ResultSet rs;
        String query = "select * from cancion where nombre like '%"+name+"%';";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString(5)!= null){
                    s = new Song(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                    
                    result.add(s);
                }else{
                    s = new Song(rs.getString(2), rs.getInt(3), rs.getInt(4));
                    s.setId(rs.getInt(1));
                    result.add(s);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Song> getSongsbyDisk(int disk_id){
        ArrayList<Song> result = new ArrayList();
        ResultSet rs;
        String query = "select * from cancion where cancion.iddisco="+disk_id+";";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString(5)!= null){
                    s = new Song(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                    
                    result.add(s);
                }else{
                    s = new Song(rs.getString(2), rs.getInt(3), rs.getInt(4));
                    s.setId(rs.getInt(1));
                    result.add(s);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Song> getSongsbyArtist(int artist_id){
        ArrayList<Song> result = new ArrayList();
        ResultSet rs;
        String query = "select * from cancion"
                + " join disco"
                + " join artista"
                + " where cancion.iddisco=disco.id"
                + " and disco.idartista=artista.id"
                + " and artista.id=" + artist_id + ";";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString(5)!= null){
                    s = new Song(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                    
                    result.add(s);
                }else{
                    s = new Song(rs.getString(2), rs.getInt(3), rs.getInt(4));
                    s.setId(rs.getInt(1));
                    result.add(s);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Song> getSongsbyGenre(int genre_id){
        ArrayList<Song> result = new ArrayList();
        ResultSet rs;
        String query = "select * from cancion where cancion.idgenero="+genre_id+";";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString(5)!= null){
                    s = new Song(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                    
                    result.add(s);
                }else{
                    s = new Song(rs.getString(2), rs.getInt(3), rs.getInt(4));
                    s.setId(rs.getInt(1));
                    result.add(s);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
