package com.simulatedminds.choreapplication;

/**
 * Created by Hussein on 11/20/2017.
 */

public class User {
    //Instance variables
    private String userName= ""; //empty username making sure not having null
    private String userPassword = "";

    //setters and getters
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserPassword(String password) {
        this.userPassword = password;
    }
    public String getUserPassword(){
        return userPassword;
    }



}
