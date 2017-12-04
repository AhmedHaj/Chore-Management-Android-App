package com.simulatedminds.choreapplication;

import android.os.Debug;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by Jonathan Calles on 12/4/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

//Simple User Management Singleton
public class UserManager {

    public static final String intentIndexTitle = "selectedUser";
    private static UserManager instance = null;
    private ArrayList<User> userList;

    protected UserManager() {
        //This Exists to defeat instantiation

        userList = new ArrayList<>();

    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }



    public void deleteUser(User u){
        userList.remove(u);
    }


    public void addUser(User u){
        userList.add(u);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public User getUserAt(int index) {
        return userList.get(index);
    }

}


