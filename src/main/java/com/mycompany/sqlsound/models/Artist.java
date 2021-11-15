
package com.mycompany.sqlsound.models;

import dao.DiskDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author adryc
 */
public class Artist {
    
    DiskDAO dd;
    
    private int id;
    private String name;
    private String nationality;
    private String photo;
    private ArrayList<Disk> disks;
    
    
    
    public Artist(){
        
    }
    
    public Artist(int id, String name, String nationality, String photo) throws SQLException{
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.photo = photo;
        try{
            this.disks = getDisksDB(id);
        }catch(SQLException e){
            this.disks = new ArrayList();
        }
        
    }
    
    public Artist(String name, String nationality, String photo){
        this.name = name;
        this.nationality = nationality;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public ArrayList<Disk> getDisks() throws SQLException {
        return this.disks;
    }
    
    public ArrayList<Disk> getDisksDB(int id) throws SQLException {
        dd = new DiskDAO();
        return dd.getDisksbyArtist(id);
    }

    @Override
    public String toString() {
        
        return "ID: " + id + ", nombre: " + name + ", nacionalidad: " + nationality + ", foto: " + photo;

    }
    
    
    
    
}
