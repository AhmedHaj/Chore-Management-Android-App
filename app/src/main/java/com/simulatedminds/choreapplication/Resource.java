package com.simulatedminds.choreapplication;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

public class Resource {

    //Instance variables
    private String resourceName = "Name Not Defined";
    private String resourceDescription = "Description Not Defined";

    //Constructor
    public Resource(String name, String description) {
        this.resourceName = name;
        this.resourceDescription = description;
    }

    //Standard setters and getters to manage the information in the instances of this class
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String newName) {
        resourceName = newName;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String newDescription) {
        resourceDescription = newDescription;
    }

}