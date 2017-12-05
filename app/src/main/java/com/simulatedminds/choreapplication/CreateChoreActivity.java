package com.simulatedminds.choreapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class CreateChoreActivity extends AppCompatActivity {
    private CheckBox statusCheckBox;
    private boolean status;
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
        statusCheckBox = (CheckBox)findViewById(R.id.choreStatus);
        statusCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                status = b;
            }
        });
    }


    public void onClickBtnChoreFinish (View v) throws NumberFormatException{
        boolean rewardNotAnInteger = true;
        EditText choreTitle = (EditText) findViewById(R.id.enterChoreTitle);
        EditText choreDescription = (EditText) findViewById(R.id.enterChoreDescription);
        EditText customReward = (EditText) findViewById(R.id.enterReward);
        EditText choreResource1 = (EditText) findViewById(R.id.enterResource1);
        EditText choreResource2 = (EditText) findViewById(R.id.enterResource2);
        EditText choreResource3 = (EditText) findViewById(R.id.enterResource3);
        statusCheckBox = (CheckBox) findViewById(R.id.choreStatus);

        try {
            rewardNotAnInteger = false;
            Integer.parseInt(customReward.getText().toString());
        } catch (NumberFormatException e) {
            if (!customReward.getText().toString().equals("")){
                Toast.makeText(this, "Chore reward must be an integer!", Toast.LENGTH_LONG).show();
                rewardNotAnInteger = true;
            } else {
                rewardNotAnInteger = false;
                customReward.setText("0");
            }
        }
        if(choreTitle.getText().toString().equals("") || rewardNotAnInteger){ //need to figure out how to make this exception
            if(choreTitle.getText().toString().equals(""))
                Toast.makeText(this, "You have to have at least a chore title!", Toast.LENGTH_LONG).show(); //a way to print in an emulator
        } else {
            String[] choreResources = new String[3];
            choreResources[0] = choreResource1.getText().toString();
            choreResources[1] = choreResource2.getText().toString();
            choreResources[2] = choreResource3.getText().toString();
            ChoreManager manager = ChoreManager.getInstance();
            Chore2 chore = new Chore2(choreTitle.getText().toString(),
                    choreDescription.getText().toString(), Integer.parseInt(customReward.getText().toString()), choreResources,status);
            manager.addChore(chore);
            finish();
        }


    }




}
