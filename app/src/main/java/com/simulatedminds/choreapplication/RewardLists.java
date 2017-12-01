package com.simulatedminds.choreapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by James on 2017-11-30.
 */


public class RewardLists extends AppCompatActivity {

    ListView userListView;
    // array of users
    String[] users;
    //array of points
    int[] points;

    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_create_chore);

        userListView = (ListView) findViewById(R.id.userlistview);
        //users= "input where we get the users"  (not implemented yet)
        //points = "input where we get the points" (not implemented yet)

        //ItemAdapter class
        ItemAdapter itemAdapter = new ItemAdapter(this, users, points);
        userListView.setAdapter(itemAdapter);

    }
}
