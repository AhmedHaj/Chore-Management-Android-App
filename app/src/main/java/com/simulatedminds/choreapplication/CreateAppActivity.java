package com.simulatedminds.choreapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class CreateAppActivity extends AppCompatActivity {

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
        ToggleButton Btn = (ToggleButton) findViewById(R.id.toggleButtonOfRewardSystem);
        //Guessing that this function is to see if the button gets clicked do something to it
        Btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //method to change color when clicked
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)
                    buttonView.setBackgroundColor(Color.GREEN);
                else buttonView.setBackgroundColor(Color.RED);
            }
        });
    }
}
