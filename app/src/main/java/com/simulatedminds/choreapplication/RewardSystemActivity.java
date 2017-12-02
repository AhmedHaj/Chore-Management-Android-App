package com.simulatedminds.choreapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RewardSystemActivity extends AppCompatActivity {
    public static int sizeOfArrayOfStringRewards = 5;
    public static String[] arrayOfStringRewards = new String[sizeOfArrayOfStringRewards];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_system);
    }


    public void onClickBtnRewardsCreation (View v) { //method for finish btn in RewardSystemActivity
        boolean exitIntent = false;
        EditText customReward1 = (EditText) findViewById(R.id.customReward1);
        EditText customReward2 = (EditText) findViewById(R.id.customReward2);
        EditText customReward3 = (EditText) findViewById(R.id.customReward3);
        EditText customReward4 = (EditText) findViewById(R.id.customReward4);
        EditText customReward5 = (EditText) findViewById(R.id.customReward5);
        arrayOfStringRewards[0] = customReward1.getText().toString();
        arrayOfStringRewards[1] = customReward2.getText().toString();
        arrayOfStringRewards[2] = customReward3.getText().toString();
        arrayOfStringRewards[3] = customReward4.getText().toString();
        arrayOfStringRewards[4] = customReward5.getText().toString();

        for (int i = 0; i < sizeOfArrayOfStringRewards; i++){ //check if the user gave at least 1 custom reward
            if(!arrayOfStringRewards[i].equals(""))
                exitIntent = true;
        }
        if(exitIntent){
            Intent intent = new Intent(this, NavigationDrawerActivity.class); //intent is used to launch another activity, make this intent to get passwords from users
            startActivity(intent);
        } else {
            Toast.makeText(this, "You have to have at least 1 custom reward!", Toast.LENGTH_LONG).show();
        }


    }
}
