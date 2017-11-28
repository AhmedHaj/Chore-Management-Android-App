package com.simulatedminds.choreapplication;

import java.util.Date;
import java.util.UUID;

/**
 * Created by arielkim on 2017-11-22.
 */

public class Chore {
    /*Variables are preceded by an m to make use of auto getter and setter functions in android studio.
      m stands for member. - Ariel
    */
    //UUID provides universally unique ID values - Ariel
    private UUID mId;
    // Variable holding the title of the chore - Ariel
    private String mTitle;
    // Variable that holds the details of the chore -Ariel
    private String mDetail;
    //Currently initialized to the current date, this is the default for a chore - Ariel
    private Date mDate;
    //Boolean to see if a chore has been completed or not -Ariel
    private boolean mCompleted;

    public Chore(){
        //Generates the random unique ID and passes it to the one argument constructor - Ariel
        this(UUID.randomUUID());
    }

    public Chore(UUID id){
        mId = id;
        //Currently initialized to the current date, this is the default for a chore - Ariel
        mDate = new Date();
    }

    //Setter for the chore title - Ariel
    public void setTitle(String title) {
        mTitle = title;
    }
    //Setter for the date title - Ariel
    public void setDate(Date date) {
        mDate = date;
    }
    //Getter for mId - Ariel
    public UUID getId() {
        return mId;
    }
    //Getter for getTitle - Ariel
    public String getTitle() {
        return mTitle;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    //Getter for getDate - Ariel
    public Date getDate() {
        return mDate;
    }

    //Essentially the getter for mCompleted even if the name of the method is "isCompleted" - Ariel
    public boolean isCompleted(){
        return mCompleted;
    }
    // Setter for mCompleted - Ariel
    public void setCompleted(boolean completed){
        mCompleted = completed;
    }
}
