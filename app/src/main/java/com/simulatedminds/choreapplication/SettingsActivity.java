package com.simulatedminds.choreapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }



    //-----Listeners for all the buttons on the Settings Screen -----///


    //method to use when the button ("Change Pin") gets clicked
    public void onClickBtnChangePin(View v){
        // TODO Edit Pin
        // TODO Display confirmation message/toast
    }

    //method to use when the button ("Add Regular User") gets clicked
    public void onClickBtnAddRegularUser(View v){
        Intent intent = new Intent(this, UserCreationActivity.class); //intent is used to launch another activity
        startActivity(intent);
    }

    //method to use when the button ("Add Child User") gets clicked
    public void onClickBtnAddChildUser(View v){
        Intent intent = new Intent(this, UserCreationActivity.class); //intent is used to launch another activity
        startActivity(intent);
    }

    //method to use when the button ("Add Reward To System") gets clicked
    public void onClickBtnAddRewardToSystem(View v){
    }

    //method to use when the button ("Disable Reward System") gets clicked
    // TODO This button should probably be an ON/OFF toggle instead, if we are keeping the Reward System as optional
    public void onClickBtnDisableRewardSystem(View v){
        // TODO Add Reward to System
        // TODO Display confirmation message/toast

    }

    //method to use when the button ("Clear All Chores") gets clicked
    public void onClickBtnClearAllChores(View v){
        // TODO Clear all chores
        Toast.makeText(this, "All Chores have been cleared", Toast.LENGTH_LONG).show(); //This should only display once all the chores have been deleted
    }

    //method to use when the button ("Clear All Resources") gets clicked
    public void onClickBtnClearAllResources(View v){
        // TODO Clear all resources
        Toast.makeText(this, "All Resources have been cleared", Toast.LENGTH_LONG).show(); //This should only display once all the resources have been deleted
    }
}
