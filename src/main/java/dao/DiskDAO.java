
package dao;

import utils.bdConnector;
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
public class DiskDAO {
    
    bdConnector connector = new bdConnector();
    Connection conn;
    Statement stmt;
    
    public DiskDAO() throws SQLException{
        this.conn = connector.connect();
        this.stmt = conn.createStatement();
    }
    public void insertDisk(Disk d){
        String query = "insert into disco(nombre, foto, fecha, idartista)values('"
                + d.getName() + "','" + d.getPhoto() + "','" + d.getDate() + "'," + d.getArtist_id() + ");";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public ArrayList<Disk> getAllDisks(){
        String query = "select * from disco";
        ArrayList<Disk> result = new ArrayList<>();
        Disk d;
        ResultSet disks;
        try  {
            disks = stmt.executeQuery(query);
            while (disks.next()) {
                d = new Disk(disks.getInt(1),disks.getString(2), disks.getString(3), disks.getString(4), disks.getInt(5));
                
                result.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
        return result;
    }

    public Disk getDiskbyId(int id){
        String query = "select * from disco where id=" + id + ";";

        Disk result = new Disk();
        ResultSet disks;
        try  {
            disks = stmt.executeQuery(query);
            while (disks.next()) {
                result = new Disk(disks.getInt(1),disks.getString(2), disks.getString(3), disks.getString(4), disks.getInt(5));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public ArrayList<Disk> getDisksbyName(String name){
        String query = "select * from disco where disco.nombre like '%" + name + "%';";
        ArrayList<Disk> result = new ArrayList<>();
        Disk d;
        ResultSet disks;
        try  {
            disks = stmt.executeQuery(query);
            while (disks.next()) {
                d = new Disk(disks.getInt(1),disks.getString(2), disks.getString(3), disks.getString(4), disks.getInt(5));
                result.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public void updateDisk(Disk d, int id){
        
        String query = "update disco set disco.nombre='" + d.getName()
                + "', disco.foto='" + d.getPhoto()
                + "', disco.fecha='" + d.getDate()
                + "', disco.idartista=" + d.getArtist_id()
                + " where disco.id=" + id + ";";
        try  {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void deleteDisk(int id){
        String query = "delete from disco where disco.id="+id+";";
        try{
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
       
    }
    
    public ArrayList<Disk> getDisksbyArtist(int artist_id){
        String query = "select * from disco where disco.idartista=" + artist_id + ";";
        ArrayList<Disk> result = new ArrayList<>();
        Disk d;
        ResultSet disks;
        try  {
            disks = stmt.executeQuery(query);
            while (disks.next()) {
                d = new Disk(disks.getInt(1), disks.getString(2), disks.getString(3), disks.getString(4), disks.getInt(5));
                result.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
}
