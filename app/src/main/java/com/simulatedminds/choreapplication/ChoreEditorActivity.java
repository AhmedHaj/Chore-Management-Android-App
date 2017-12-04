package com.simulatedminds.choreapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

public class ChoreEditorActivity extends AppCompatActivity {

    private TextView choreName;
    private EditText choreDescriptionline;
    private EditText customReward;
    private EditText choreResource1;
    private EditText choreResource2;
    private EditText choreResource3;

    private Chore2 chore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_editor); // The layout is being modified

        //Getting the incomming intent
        Intent intent = getIntent();
        int choreIndex = intent.getIntExtra(ChoreManager.intentIndexTitle,0); //0 is a "default return value"

        //Getting TextFields we are about to update
        choreName = (TextView) findViewById(R.id.editChoreTitle);
        choreDescriptionline = (EditText) findViewById(R.id.editDescription);
        customReward = (EditText) findViewById(R.id.enterReward);
        choreResource1 = (EditText) findViewById(R.id.enterResource1);
        choreResource2 = (EditText) findViewById(R.id.enterResource2);
        choreResource3 = (EditText) findViewById(R.id.enterResource3);


        //Getting corresponding Recipe
        chore = ChoreManager.getInstance().getChoreAt(choreIndex);

        //Updating contents in this screen
        choreName.setText(chore.getChoreTitle());
        choreDescriptionline.setText(chore.getChoreDescription());
        customReward.setText(Integer.toString(chore.getChoreReward()));
        final String[] choreResources = chore.getChoreResources();
        choreResource1.setText(choreResources[0]);
        choreResource2.setText(choreResources[1]);
        choreResource3.setText(choreResources[2]);

        //Updating Function of OnClick Button (Save)
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Updating contents in variable
                boolean rewardNotAnInteger = true;
                chore.setChoreTitle(choreName.getText().toString());
                chore.setChoreDescription(choreDescriptionline.getText().toString());
                chore.setChoreReward(Integer.parseInt(customReward.getText().toString()));
                choreResources[0] = choreResource1.getText().toString();
                choreResources[1] = choreResource2.getText().toString();
                choreResources[2] = choreResource3.getText().toString();
                chore.setChoreResources(choreResources);
                try {
                    rewardNotAnInteger = false;
                    Integer.parseInt(customReward.getText().toString());
                } catch (NumberFormatException e) {
                    if (!customReward.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Chore reward must be an integer!", Toast.LENGTH_LONG).show();
                        rewardNotAnInteger = true;
                    } else {
                        rewardNotAnInteger = false;
                        customReward.setText("0");
                    }
                }
                if(choreName.getText().toString().equals("") || rewardNotAnInteger){ //need to figure out how to make this exception
                    if(choreName.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(), "You have to have at least a chore title!", Toast.LENGTH_LONG).show(); //a way to print in an emulator
                }
                finish();
            }
        });

        //Updating Function of OnClick Button (Cancel)
        Button cancelButton = (Button) findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Updating Function of OnClick Button (Delete)
        Button deleteButton = (Button) findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChoreManager.getInstance().deleteChore(chore);
                finish();
            }
        });


    }
}
