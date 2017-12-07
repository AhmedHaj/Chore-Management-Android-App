package com.simulatedminds.choreapplication;

/**
 * Created by Hussein on 11/20/2017.
 */

public class User {
    //Instance variables
    private String userName= ""; //empty username making sure not having null
    private String userPassword = "";
    private int userPoints = 0;
    private boolean isRestricted = false;

    public User(){}
    public User(boolean isRestricted){}
    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPoints = 0;
    }

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
    public int getUserPoints() {return userPoints;}
    public void setUserPoints(int userPoints) {this.userPoints = userPoints;}
    public boolean getRestriction() {return isRestricted;}
    public void setRestricted(boolean isRestricted) {this.isRestricted = isRestricted;}




}
