
package com.mycompany.sqlsound.models;

import dao.ArtistDAO;
import dao.SongDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adryc
 */
public class Disk {
    
    ArtistDAO ad;
    SongDAO sd;
    
    private int id;
    private String name;
    private String photo;
    private String date;
    private ArrayList<Song> songs;
    private int artist_id;

    public Disk() {
    }

    public Disk(int id, String name, String photo, String date, int artist_id) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.date = date;
        this.songs = songs;
        this.artist_id = artist_id;
        try{
            this.songs = getSongsDB(id);
        }catch(SQLException e){
            this.songs = new ArrayList();
        }
    }
    
    public Disk(String name, String photo, String date, ArrayList<Song> songs, int artist_id) {
        this.name = name;
        this.photo = photo;
        this.date = date;
        this.songs = songs;
        this.artist_id = artist_id;
    }
    
    public Disk(String name, String photo, String date, int artist_id) {
        this.name = name;
        this.photo = photo;
        this.date = date;
        this.songs = new ArrayList<>();
        this.artist_id = artist_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
    
    public ArrayList<Song> getSongsDB(int id) throws SQLException {
        sd = new SongDAO();
        return sd.getSongsbyDisk(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getArtistName() throws SQLException{
        ad = new ArtistDAO();
        Artist a = ad.getArtistbyId(artist_id);
        return a.getName();
    }
    
    @Override
    public String toString() {
        try {
            return ("Disco " + id + ": " + name + ", foto: " + photo+", autor: "+getArtistName()+", lanzamiento: "+date);
        } catch (SQLException e) {
            return("Error");
        }
    }
    
    
    
    
}
