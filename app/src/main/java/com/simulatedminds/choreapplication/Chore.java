package com.simulatedminds.choreapplication;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

public class Chore {

    //Instance variables
    private String choreName = "Name Not Defined";
    private String choreDescription = "Description Not Defined";

    //Constructor
    public Chore(String name, String description) {
        this.choreName = name;
        this.choreDescription = description;
    }

    //Standard setters and getters to manage the information in the instances of this class
    public String getChoreName() {
        return choreName;
    }

    public void setChoreName(String newName) {
        choreName = newName;
    }

    public String getChoreDescription() {
        return choreDescription;
    }

    public void setChoreDescription(String newDescription) {
        choreDescription = newDescription;
    }

}