package com.simulatedminds.choreapplication;

/**
 * Created by Hussein on 2017-11-30.
 */

public class Chore2 {
    private String choreTitle = " ";
    private String choreDescription = " ";
    private String[] choreResources = {"", "", ""};
    private String[] assignedUsers = {"", "", ""};
    private int choreReward = 0;
    private boolean status = false;


    public Chore2(String choreTitle, String choreDescription, int choreReward, String[] choreResources,boolean status) {
        //to be added as last parameter  String[] assignedUsers
        this.choreTitle = choreTitle;
        this.choreDescription = choreDescription;
        this.choreResources = choreResources;
        this.choreReward = choreReward;
        this.status = status;
        this.assignedUsers = assignedUsers;
    }

    //----------getters and setters ------------
    public String getChoreTitle(){
        return choreTitle;
    }
    public String getChoreDescription(){
        return choreDescription;
    }
    public int getChoreReward(){
        return choreReward;
    }
    public String[] getAssignedUsers(){
        return assignedUsers;
    }
    public String[] getChoreResources(){
        return choreResources;
    }
    public void setChoreReward(int reward){
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
    public void setStatus(boolean status){this.status = status;}
    public boolean getStatus(){return status;}
}
