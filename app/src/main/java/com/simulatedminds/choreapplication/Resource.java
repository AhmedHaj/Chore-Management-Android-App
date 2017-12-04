package com.simulatedminds.choreapplication;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

public class Resource {

    //Instance variables
    private String resourceName = "Name Not Defined";
    private String chore;

    //Constructor
    public Resource() {
    }

    public Resource(String name, String chore) {
        this.resourceName = name;
        this.chore = chore;
    }

    //Standard setters and getters to manage the information in the instances of this class
    public String getResourceName() {
        return resourceName;
    }

    public String getChore() {
        return chore;
    }

    public void setResourceName(String newName) {
        resourceName = newName;
    }

    public void setChore(String chore) {
        this.chore = chore;
    }

}