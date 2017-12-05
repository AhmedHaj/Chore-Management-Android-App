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


        //Update contents in this screen to match with the User that was clicked, and therefore the text that will be edited by the user
        userName.setText(user.getUserName()); //These correspond to the getters in the User java class
        userPassword.setText(user.getUserPassword()); //These correspond to the getters in the User java class


        /*
        //Listener and function when  button ("Save") is clicked
        Button saveButton = (Button) findViewById(R.id.buttonSaveUser);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update the instance of User using the setter methods, using the text from the textfields
                user.setUserName(userName.getText().toString());
                user.setUserPassword(userPassword.getText().toString());

                //TODO: May want to program some checks / catch exceptions if null, etc.
                finish();
            }
        });




        //Listener and function when  button ("Cancel") is clicked
        Button cancelButton = (Button) findViewById(R.id.buttonCancelUser);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//Nothing to be done, so just close the current activity
            }
        });


        //Listener and function when  button ("Delete") is clicked
        Button deleteButton = (Button) findViewById(R.id.buttonDeleteUser);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager.getInstance().deleteUser(user); //delete the instance of User
                finish(); //close current activity
            }
        });
        */




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
