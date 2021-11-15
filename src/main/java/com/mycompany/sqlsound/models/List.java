
package com.mycompany.sqlsound.models;

import dao.ListDAO;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author adryc
 */
public class List {
    
    private int id;
    private String name;
    private String description;
    private int user_id;
    private ArrayList<User> suscribers;
    private ArrayList<Song> songs;

    public List() {
    }

    public List(String name, String description, int user_id) {
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        songs = new ArrayList();
    }
    
    public List(int id, String name, String description, int user_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        try{
            this.songs = getSongsDB(id);
        }catch(SQLException e){
            this.songs = new ArrayList();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<User> getSuscribers() {
        return suscribers;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSongs(ArrayList<Song> songs) throws SQLException {
        this.songs = songs;
    }
    
    public ArrayList<Song> getSongsDB(int id) throws SQLException {
        ListDAO ld = new ListDAO();
        return ld.getSongsfromList(id);
    }

    public void setSuscribers(ArrayList<User> suscribers) {
        this.suscribers = suscribers;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public String getUsername(){
        String result = "";
        try{
            UserDAO udao = new UserDAO();
            if(udao.getUserbyId(this.id) != null){
                result = udao.getUserbyId(this.user_id).getNickname();
            }else{
                result = "Desconocido";
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String toString() {
        return ("Lista "+this.id+" nombre: "+this.name+" descripción:"+this.description+", creada por: "+getUsername());
    }
    
    
    
    
}
