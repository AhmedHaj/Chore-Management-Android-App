package com.simulatedminds.choreapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

public class ChoreEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_editor); // The layout is being modified

        //Getting the incomming intent
        Intent intent = getIntent();
        int choreIndex = intent.getIntExtra(ChoreManager.intentIndexTitle,0); //0 is a "default return value"

        //Getting TextFields we are about to update
        final TextView choreName = (TextView) findViewById(R.id.line01);
        final EditText choreDescriptionline = (EditText) findViewById(R.id.line02);

        //Getting corresponding Recipe
        final Chore chore = ChoreManager.getInstance().getChoreAt(choreIndex);

        //Updating contents in this screen
        choreName.setText(chore.getChoreName());
        choreDescriptionline.setText(chore.getChoreDescription());

        //Updating Function of OnClick Button (Save)
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Updating contents in variable
                chore.setChoreName(choreName.getText().toString());
                chore.setChoreDescription(choreDescriptionline.getText().toString());

                //TODO: Save changed recipe information back in to recipe (I don't do it in the examples as students have to implement their own logic)
                finish();
            }
        });

        //Updating Function of OnClick Button (Cancel)
        Button cancelButton = (Button) findViewById(R.id.buttonCancel);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
