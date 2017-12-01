package com.simulatedminds.choreapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

public class ResourceEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_editor);

        //Getting the incomming intent
        Intent intent = getIntent();
        int resourceIndex = intent.getIntExtra(ResourceManager.intentIndexTitle,0); //0 is a "default return value"

        //Getting TextFields we are about to update
        final TextView resourceName = (TextView) findViewById(R.id.line01);
        final EditText resourceDescriptionline = (EditText) findViewById(R.id.line02);

        //Getting corresponding Recipe
        final Resource resource = ResourceManager.getInstance().getResourceAt(resourceIndex);

        //Updating contents in this screen
        resourceName.setText(resource.getResourceName());
        resourceDescriptionline.setText(resource.getResourceDescription());

        //Updating Function of OnClick Button (Save)
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Updating contents in variable
                resource.setResourceName(resourceName.getText().toString());
                resource.setResourceDescription(resourceDescriptionline.getText().toString());

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
