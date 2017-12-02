package com.simulatedminds.choreapplication;

/**
 * Created by Hussein on 2017-11-30.
 */

public class Chore2 {
    private String choreTitle = " ";
    private String choreDescription = " ";
    private String[] choreResources;
    private String[] assignedUsers;
    private String choreReward = " ";


    public Chore2(String choreTitle, String choreDescription, String choreReward, String[] choreResources) {
        //to be added as last parameter  String[] assignedUsers
        this.choreTitle = choreTitle;
        this.choreDescription = choreDescription;
        this.choreResources = choreResources;
        this.choreReward = choreReward;
        this.assignedUsers = assignedUsers;
    }

    //----------getters and setters ------------
    public String getChoreTitle(){
        return choreTitle;
    }
    public String getChoreDescription(){
        return choreDescription;
    }
    public String getChoreReward(){
        return choreReward;
    }
    public String[] getAssignedUsers(){
        return assignedUsers;
    }
    public String[] getChoreResources(){
        return choreResources;
    }
    public void setChoreReward(String reward){
        choreReward = reward;
    }
    public void setChoreTitle(String title){
        choreTitle = title;
    }
    public void setChoreDescription(String description){
        choreDescription = description;
    }
    public void setAssignedUsers(String[] assignedUsers){
        this.assignedUsers = assignedUsers;
    }
    public void setChoreResources(String[] choreResources){
        this.choreResources = choreResources;
    }
}
