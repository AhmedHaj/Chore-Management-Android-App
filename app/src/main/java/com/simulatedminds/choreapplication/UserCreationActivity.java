package com.simulatedminds.choreapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserCreationActivity extends AppCompatActivity {

    private User user;
    private EditText userName;
    private EditText userPassword;
    private EditText userPoints;
    private UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //Getting the incomming intent
        Intent intent = getIntent();
        int userIndex = intent.getIntExtra(UserManager.intentIndexTitle,0); //0 is a "default return value"

        //Get the UserManager, the singleton class that manages the users in a way that is accessible to all other activities in the app
        manager = UserManager.getInstance();

        //Identify TextFields on the screen to identify the information contained in them.
        userName = (EditText) findViewById(R.id.createUserNameText); //These correspond to the IDs of the textfields in the layout file
        userPassword = (EditText) findViewById(R.id.createUserPasswordText); //These correspond to the IDs of the textfields in the layout file

        userPoints = (EditText) findViewById(R.id.createUserPointsText); //Currently not used, but here in case of future use


    }







    //method to use when the button ("Cancel") gets clicked
    public void onClickBtnUserCreateCancel(View v){
        //return to User list
        Intent intentGoBack = new Intent(this, UserSelectActivity.class); //intent is used to launch another activity
        startActivity(intentGoBack);
    }


    //method to use when the button ("Save") gets clicked
    public void onClickBtnUserCreateSave(View v){

        //Try to create a User, using the information in the textfields
        try {
            User tempUser = new User(userName.getText().toString(), userPassword.getText().toString());
            manager.addUser(tempUser);
        }

        //If fails, create a User with the default information
        //TODO: Change this to validation of correct input before allowing a user to be created
        catch (Exception e){
            int tempInt = manager.getUserSize();
            User tempUser = new User("User Name" + tempInt, "0000");
            manager.addUser(tempUser);
        }

        //return to User list
        Intent intentGoBack = new Intent(this, UserSelectActivity.class); //intent is used to launch another activity
        startActivity(intentGoBack);
    }





}
