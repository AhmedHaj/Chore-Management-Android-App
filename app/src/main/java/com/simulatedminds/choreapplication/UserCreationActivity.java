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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        final EditText userTitle = (EditText) findViewById(R.id.enterUserTitle);
        userTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTitle.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);                inputMethodManager.toggleSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
            }
        });
    }







    public void onClickBtnAccountsCreation (View v) throws NumberFormatException{

        UserManager manager = UserManager.getInstance();

        //EditText userTitle = (EditText) findViewById(R.id.enterUserTitle);

        String tempPassword = "0000";

        //User tempUser = new User(userTitle.getText().toString(), userTitle.getText().toString());
                //choreDescription.getText().toString(), Integer.parseInt(customReward.getText().toString()), choreResources);
        //manager.addUser(tempUser);


        Intent intent = new Intent(this, NavigationDrawerActivity.class); //The reason it was crashing before was because ChoreListActivity2 is a fragment, and it was trying to call that.
        startActivity(intent);



    }





}
