package com.simulatedminds.choreapplication;

import android.os.Debug;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

//Simple Recipe Management Singleton
public class ChoreManager {

    public static final String intentIndexTitle = "selectedChore";
    private static ChoreManager instance = null;
    private ArrayList<Chore> choreList;

    protected ChoreManager() {
        //This Exists to defeat instantiation

        String[] values = new String[]{
                "Wash Car", "Walk the Dog", "Make Dinner", "Vacuum", "Take out the Trash", "Feed the Cat", "Do the Dishes", "Pay the bills", "Clean the bathroom", "Go Grocery Shopping"
        };

        choreList = new ArrayList<>();

        for (int i = 0; i < values.length ; i++) {
            Chore newChore = new Chore(values[i],"Chore Description has not been defined.");
            choreList.add(newChore);
        }
    }

    public static ChoreManager getInstance() {
        if (instance == null) {
            instance = new ChoreManager();
        }
        return instance;
    }

    public ArrayList<Chore> getChoreList() {
        return choreList;
    }

    public Chore getChoreAt(int index) {
        return choreList.get(index);
    }
}