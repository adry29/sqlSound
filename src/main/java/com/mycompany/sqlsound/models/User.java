
package com.mycompany.sqlsound.models;

/**
 *
 * @author adryc
 */
public class User {
    
    private int id;
    private String nickname;
    private String password;

    public User() {
    }

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
    
    public User(int id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ("Usuario "+this.id+": "+this.nickname);
    }
    
    
    
}
