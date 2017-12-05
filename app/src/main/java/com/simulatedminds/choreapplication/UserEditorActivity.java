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


/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */


public class UserEditorActivity extends AppCompatActivity {

    private User user;
    private EditText userName;
    private EditText userPassword;
    private EditText userPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_editor); // The layout is being modified


        //Getting the incomming intent
        Intent intent = getIntent();
        int userIndex = intent.getIntExtra(UserManager.intentIndexTitle,0); //0 is a "default return value"


        //Getting corresponding User, the one that was clicked to activate this Edit User Activity
        user = UserManager.getInstance().getUserAt(userIndex);

        //Identify TextFields that need to be populated
        userName = (EditText) findViewById(R.id.editUserNameText); //These correspond to the IDs of the textfields in the layout file
        userPassword = (EditText) findViewById(R.id.editUserPasswordText); //These correspond to the IDs of the textfields in the layout file
        userPoints = (EditText) findViewById(R.id.editUserPointsText); //These correspond to the IDs of the textfields in the layout file

        //Update contents in this screen to match with the User that was clicked, and therefore the text that will be edited by the user
        userName.setText(user.getUserName()); //These correspond to the getters in the User java class
        userPassword.setText(user.getUserPassword()); //These correspond to the getters in the User java class
        userPoints.setText(Integer.toString(user.getUserPoints())); //These correspond to the getters in the User java class


    }







    //method to use when the button ("Cancel") gets clicked
    public void onClickBtnUserEditCancel(View v){
        //return to User list
        Intent intentGoBack = new Intent(this, UserSelectActivity.class); //intent is used to launch another activity
        startActivity(intentGoBack);
    }


    //method to use when the button ("Save") gets clicked
    public void onClickBtnUserEditSave(View v){
        //Getting the incomming intent
        Intent intent = getIntent();
        int userIndex = intent.getIntExtra(UserManager.intentIndexTitle,0); //0 is a "default return value"

        //Getting corresponding User, the one that was clicked to activate this Edit User Activity
        user = UserManager.getInstance().getUserAt(userIndex);
        //Update the instance of User using the setter methods, using the text from the textfields
        user.setUserName(userName.getText().toString());
        user.setUserPassword(userPassword.getText().toString());

        int tempInt;
        try {
            tempInt = Integer.parseInt(userPassword.getText().toString());
            user.setUserPoints(tempInt);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Points must be an integer", Toast.LENGTH_LONG).show(); //a way to print in an emulator
        }


        //TODO: May want to program some checks / catch exceptions if null, etc.

        //return to User list
        Intent intentGoBack = new Intent(this, UserSelectActivity.class); //intent is used to launch another activity
        startActivity(intentGoBack);
    }


    //method to use when the button ("Delete") gets clicked
    public void onClickBtnUserEditDelete (View v){

        //Getting the incomming intent
        Intent intent = getIntent();
        int userIndex = intent.getIntExtra(UserManager.intentIndexTitle,0); //0 is a "default return value"

        //Getting corresponding User, the one that was clicked to activate this Edit User Activity
        user = UserManager.getInstance().getUserAt(userIndex);
        UserManager.getInstance().deleteUser(user); //Delete that User instance

        //return to User list
        Intent intentGoBack = new Intent(this, UserSelectActivity.class); //intent is used to launch another activity
        startActivity(intentGoBack);
    }







}
