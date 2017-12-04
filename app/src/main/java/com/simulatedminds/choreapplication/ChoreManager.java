package com.simulatedminds.choreapplication;

import android.os.Debug;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

//Simple Chore Management Singleton
public class ChoreManager {

    public static final String intentIndexTitle = "selectedChore";
    private static ChoreManager instance = null;
    private ArrayList<Chore2> choreList;

    protected ChoreManager() {
        //This Exists to defeat instantiation

        choreList = new ArrayList<>();

    }

    public static ChoreManager getInstance() {
        if (instance == null) {
            instance = new ChoreManager();
        }
        return instance;
    }

    public void deleteChore(Chore2 c){
        choreList.remove(c);
    }


    public void addChore(Chore2 c){
        choreList.add(c);
    }

    public ArrayList<Chore2> getChoreList() {
        return choreList;
    }

    public Chore2 getChoreAt(int index) {
        return choreList.get(index);
    }
}