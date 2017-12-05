package com.simulatedminds.choreapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class ViewChoreActivity extends AppCompatActivity {

    private TextView choreName;
    private TextView choreDetails;
    private Chore2 chore;
    private int choreIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chore);

        //Getting the incomming intent
        Intent intent = getIntent();
        int choreIndex = intent.getIntExtra(ChoreManager.intentIndexTitle,0); //0 is a "default return value"
        this.choreIndex = choreIndex;
        getChoreDetails();


    }



    private void setChoreMarkButtonColour(){  //sets correct text and colour of button
        Button btn = (Button) findViewById(R.id.markedChoreButton);
        if (chore.getStatus()){
            btn.setBackgroundColor(Color.GREEN);
            btn.setText("Complete");
        } else {
            btn.setBackgroundColor(Color.RED);
            btn.setText("Incomplete");
        }
    }

    public void setChoreMarkButton(View v){ //toggle button for marking a chore
        Button button = (Button) v;
        if(chore.getStatus()){
            button.setBackgroundColor(Color.RED);
            button.setText("Incomplete");
            chore.setStatus(false);
        } else {
            button.setBackgroundColor(Color.GREEN);
            button.setText("Complete");
            chore.setStatus(true);
        }
    }

    public void onClickEditChore(View v){
        ChoreEditorActivity.chore = chore;
        Intent intent = new Intent(this, ChoreEditorActivity.class);
        startActivity(intent);
    }

    public void getChoreDetails(){
        choreName = (TextView) findViewById(R.id.getChoreTitle);
        choreDetails = (TextView) findViewById(R.id.getChoreDetails);

        //Getting corresponding Recipe
        chore = ChoreManager.getInstance().getChoreAt(choreIndex);
        setChoreMarkButtonColour();
        String reallyLongString = "";
        choreName.setText("Chore Title: " + chore.getChoreTitle());
        if (chore.getChoreDescription() != "")
            reallyLongString += "\nChore Description: " + chore.getChoreDescription() + "\n";
        boolean choreResources = false;
        for (int i = 0; i < chore.getChoreResources().length; i++){
            if (chore.getChoreResources()[i] != "" && !choreResources){
                choreResources = true;
                reallyLongString += "\nResources: \n";
            }
            if(choreResources && chore.getChoreResources()[i] != "")
                reallyLongString += "\t\t\t\t\t" + i + ") " + chore.getChoreResources()[i] + "\n";
        }

        reallyLongString += "\nReward: " + chore.getChoreReward() + "\n";
        reallyLongString += "\nAssignment: \n";
        for (int j = 0; j < chore.getAssignedUsers().length; j++)
            reallyLongString += "\t\t\t\t\t" + j + ") " + chore.getAssignedUsers()[j] + "\n";
        choreDetails.setText(reallyLongString);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getChoreDetails();
    }

    public void onClickDoneView(View v){ //make it return to homepage chorelist
        finish();

    }

}
