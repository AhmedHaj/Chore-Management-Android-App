package com.simulatedminds.choreapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CreateAppActivity extends AppCompatActivity {

    //instance variables
    private static int numberOfRegularUsers;
    private static int numberOfChildUsers;
    private static boolean passwordSystem = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app);

        //code found at https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list
        //ask TA if we can use this, i don't see why not
        //initializes spiiner and gets spinner id from activity_create_app XML
        Spinner regularDropdown = (Spinner)findViewById(R.id.regularSpinner);
        Spinner childDropdown = (Spinner)findViewById(R.id.childSpinner);
        //list of items for the spinner
        String[] items = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        //creates an adapter to describe how the items are displayed
        ArrayAdapter<String> regularAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> childAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one
        regularDropdown.setAdapter(regularAdapter);
        childDropdown.setAdapter(childAdapter);

        //code found at https://stackoverflow.com/questions/11978880/how-to-change-color-of-the-toggle-button
        //get id of button from XML layout and set it to Btn
        ToggleButton passwordToggleButton = (ToggleButton) findViewById(R.id.toggleButtonOfPasswordProtection);
        passwordToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //method to change color when clicked
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked) {
                    buttonView.setBackgroundColor(Color.GREEN);
                    setPasswordSystem(true);
                } else {
                    buttonView.setBackgroundColor(Color.RED);
                    setPasswordSystem(false);
                }
            }
        });
    }

    //method to use when getStarted gets clicked
    public void onClickBtnBack(View v){
        Intent intent = new Intent(this, MainActivity.class); //intent is used to launch another activity
        startActivity(intent);
    }

    public void onClickBtnNext(View v){
        //gets spinners and store them in these variables
        Spinner myRegularUserSpinner=(Spinner) findViewById(R.id.regularSpinner);
        Spinner myChildUserSpinner=(Spinner) findViewById(R.id.childSpinner);

        //store applicant's choice of # of users
        int numberOfRegularUsersByApplicant = Integer.parseInt(myRegularUserSpinner.getSelectedItem().toString());
        int numberOfChildUsersByApplicant = Integer.parseInt(myChildUserSpinner.getSelectedItem().toString());

        //store applicant's choice of # of users in instance variables
        setNumberOfRegularUsers(numberOfRegularUsersByApplicant);
        setNumberOfChildUsers(numberOfChildUsersByApplicant);

        //checks if there is at least one regular user to be able to go to the next activity
        if (getNumberOfRegularUsers() > 0){

            //Use the UserManager (singleton class) to manage the users in a way that is accessible to all other activities in the app
            UserManager manager = UserManager.getInstance();

            //creates temp Regular Users
            for (int i = 0; i < getNumberOfRegularUsers(); i++){
                User tempUser = new User("User Name" + i, "0000");
                manager.addUser(tempUser);
            }

            //creates temp Child Users
            for (int i = 0; i < getNumberOfChildUsers(); i++){
                User tempUser = new User("Child Name" + i, "0000");
                manager.addUser(tempUser);
            }



            Intent intent = new Intent(this, NavigationDrawerActivity.class); //intent is used to launch another activity
            startActivity(intent);
        } else {
            Toast.makeText(this, "Number of regular users should be greatar than zero", Toast.LENGTH_LONG).show(); //a way to print in an emulator
        }
    }
    // ---------- Getters and Setters ----------
    public static void setNumberOfRegularUsers(int users){
        numberOfRegularUsers = users;
    }

    public static void setNumberOfChildUsers(int users){
        numberOfChildUsers = users;
    }

    public static int getNumberOfRegularUsers(){
        return numberOfRegularUsers;
    }

    public static int getNumberOfChildUsers(){
        return numberOfChildUsers;
    }



    public static boolean getPasswordSystem(){
        return passwordSystem;
    }

    public static void setPasswordSystem(boolean choice){
        passwordSystem = choice;
    }

    //-------------------------------------------
}
