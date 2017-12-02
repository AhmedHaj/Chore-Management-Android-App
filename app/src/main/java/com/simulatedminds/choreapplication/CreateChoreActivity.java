package com.simulatedminds.choreapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class CreateChoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chore);

        final EditText choreTitle = (EditText) findViewById(R.id.enterChoreTitle);
        choreTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choreTitle.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);                inputMethodManager.toggleSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
            }
        });
    }


    public void onClickBtnChoreFinish (View v) {
        //Toast.makeText(this, "I am in here", Toast.LENGTH_LONG).show();
        EditText choreTitle = (EditText) findViewById(R.id.enterChoreTitle);
        EditText choreDescription = (EditText) findViewById(R.id.enterChoreDescription);
        EditText customReward = (EditText) findViewById(R.id.enterReward);
        EditText choreResource1 = (EditText) findViewById(R.id.enterResource1);
        EditText choreResource2 = (EditText) findViewById(R.id.enterResource2);
        EditText choreResource3 = (EditText) findViewById(R.id.enterResource3);
        //TextView chore1 = (TextView) findViewById(R.id.chore);



        if(choreTitle.getText().toString().equals("")){ //need to figure out how to make this exception
            Toast.makeText(this, "You have to have at least a chore title!", Toast.LENGTH_LONG).show(); //a way to print in an emulator
        } else {
            String[] choreResources = new String[3];
            choreResources[0] = choreResource1.getText().toString();
            choreResources[1] = choreResource2.getText().toString();
            choreResources[2] = choreResource3.getText().toString();
            int position = ChoreListActivity2.sizeOfChoreList;
            Chore2[] newChoreList = new Chore2[++ChoreListActivity2.sizeOfChoreList];
            for (int i = 0; i < position; i++) //copying contents into new array
                newChoreList[i] = ChoreListActivity2.choreList[i];

            ChoreListActivity2.choreList = newChoreList;
            ChoreListActivity2.choreList[position] = new Chore2(choreTitle.getText().toString(),
                    choreDescription.getText().toString(), customReward.getText().toString(), choreResources);
            Intent intent = new Intent(this, NavigationDrawerActivity.class); //intent is used to launch another activity, make this intent to go to homepage
            startActivity(intent);
        }
    }




}
