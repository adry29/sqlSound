
package com.mycompany.sqlsound.models;

import dao.DiskDAO;
import dao.GenreDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adryc
 */
public class Song {
    
    private int id;
    private String name;
    private int duration;
    int genre_id;
    int disk_id;

    public Song() {
    }
    
    public Song(int id, String name, int duration, int disk_id, int genre_id) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.genre_id = genre_id;
        this.disk_id = disk_id;
    }
    
    public Song(String name, int duration, int disk_id, int genre_id) {
        this.name = name;
        this.duration = duration;
        this.genre_id = genre_id;
        this.disk_id = disk_id;
    }
    
    public Song(String name, int duration, int disk_id) {
        this.name = name;
        this.duration = duration;
        this.disk_id = disk_id;
        this.genre_id = 0;
    }
    
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getGenreId() {
        return genre_id;
    }
    
    public Genre getGenre() throws SQLException{
        GenreDAO gd = new GenreDAO();
        Genre g = gd.getGenrebyId(this.genre_id);
        return g;
    }

    public int getDiskid() {
        return disk_id;
    }
    
    public Disk getDisk() throws SQLException{
        DiskDAO dd = new DiskDAO();
        Disk result = dd.getDiskbyId(this.disk_id);
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenreId(int genre_id) {
        this.genre_id = genre_id;
    }

    public void setDiskid(int disk_id) {
        this.disk_id = disk_id;
    }
    
    

    
    
    @Override
    public String toString() {
        try {
            if(getGenre() != null){
                return ("Canción " + id + ": " + name + ", duración: " + duration + ", género: " + getGenre().getName() + ", from disk: "+getDisk().getName());
            }else{
                return ("Canción " + id + ": " + name + ", duración: " + duration + ", género: NULL, del disco: "+getDisk().getName());
            }
        } catch (SQLException ex) {
            return "Error";
        }
    }
}
