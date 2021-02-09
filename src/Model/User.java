/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import utils.LoginManager;

/**
 *
 * @author michaelaruiz
 */
public class User extends LoginManager{
    
    
    private int userId;
    private String username;
    private String password;
    private int active;
   
    
    
    
    public User(int userId, String username, String password, int active,
    String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
        super(createDate, createdBy, lastUpdate, lastUpdateBy);
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.active = active;
       
       
        
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    
}

    

