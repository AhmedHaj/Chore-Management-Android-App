package com.simulatedminds.choreapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

public class ChoreEditorActivity extends AppCompatActivity {

    private TextView choreName;
    private TextView choreAssignment;
    private EditText choreDescriptionline;
    private EditText customReward;
    private EditText choreResource1;
    private EditText choreResource2;
    private EditText choreResource3;
    private CheckBox statusCheckBox;
    public static Chore2 chore;
    Button assignUsers;
    String[] listOfUserNames;
    String[] listOfUserNamesSelected;
    String[] listOfUsersAssigned;
    boolean[] checkedItems;
    int choreMark = 0;
    ArrayList<Integer> usersSelectedItemes = new ArrayList<>();

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
        statusCheckBox = (CheckBox) findViewById(R.id.choreStatus);
        choreAssignment = (TextView) findViewById(R.id.assignmentTextView);


        //Getting corresponding Recipe
        chore = ChoreManager.getInstance().getChoreAt(choreIndex);
        if (chore.getStatus()){
            choreMark = 1;
        } else {
            choreMark = 0;
        }

        //Updating contents in this screen
        choreName.setText(chore.getChoreTitle());
        choreDescriptionline.setText(chore.getChoreDescription());
        customReward.setText(Integer.toString(chore.getChoreReward()));
        final String[] choreResources = chore.getChoreResources();
        choreResource1.setText(choreResources[0]);
        choreResource2.setText(choreResources[1]);
        choreResource3.setText(choreResources[2]);
        statusCheckBox.setChecked(chore.getStatus());


        if(chore.getAssignedUsers() != null &&chore.getAssignedUsers().length != 0){
            String choreAssignmentString = " ";
            for (int i = 0; i < chore.getAssignedUsers().length; i++)
                choreAssignmentString += chore.getAssignedUsers()[i] + "\n";
            choreAssignment.setText(choreAssignmentString);
        }



        statusCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chore.setStatus(b);
            }
        });

        // REFERENCE TO THE CODE BELOW IS https://github.com/codingdemos/MultichoiceTutorial/blob/master/app/src/main/java/com/example/multichoicetutorial/MainActivity.java
        final UserManager userManager = UserManager.getInstance();
        assignUsers = (Button) findViewById(R.id.assignUsersBtn);

        listOfUserNames = new String[userManager.getUserSize()];
        for (int i = 0; i < userManager.getUserSize(); i++){
            listOfUserNames[i] = userManager.getUserAt(i).getUserName();
        }

        checkedItems = new boolean[userManager.getUserSize()];
        assignUsers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder assignUsersBuilder = new AlertDialog.Builder(ChoreEditorActivity.this);
                assignUsersBuilder.setTitle("Select users to assign chore to!");
                assignUsersBuilder.setMultiChoiceItems(listOfUserNames, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!usersSelectedItemes.contains(position)) {
                                usersSelectedItemes.add(position);
                            } else {
                                usersSelectedItemes.remove(position);
                            }
                        }
                    }

                });
                assignUsersBuilder.setCancelable(false);
                assignUsersBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //gets called after assigning users
                        if(usersSelectedItemes.size() != 0){
                            listOfUserNamesSelected = new String[usersSelectedItemes.size()];
                            if (chore.getAssignedUsers() == null){
                                chore.setAssignedUsers(new String[usersSelectedItemes.size()]);
                            } else {
                                chore.setAssignedUsers(new String[usersSelectedItemes.size()]);
                            }
                            for (int j = 0; j < usersSelectedItemes.size(); j++){
                                listOfUserNamesSelected[j] = userManager.getUserAt(usersSelectedItemes.get(j)).getUserName();
                                chore.getAssignedUsers()[j] = listOfUserNamesSelected[j];
                            }
                        }


                    }
                });

                assignUsersBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                assignUsersBuilder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            usersSelectedItemes.clear();
                        }
                    }
                });

                AlertDialog mDialog = assignUsersBuilder.create();
                mDialog.show();

            }
        });
        //--------------------------------------------------------------------------------------------

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

                if (chore.getStatus() && choreMark == 0){
                    String string = "";
                    listOfUsersAssigned = new String[chore.getAssignedUsers().length];
                    listOfUsersAssigned = chore.getAssignedUsers();
                    for(int i = 0; i < listOfUsersAssigned.length; i++){
                        for(int j = 0; j < userManager.getUserSize(); j++) {
                            if(userManager.getUserAt(j).getUserName().equals(listOfUsersAssigned[i])) {
                                userManager.getUserAt(j).setUserPoints(userManager.getUserAt(j).getUserPoints() + chore.getChoreReward());
                                string += userManager.getUserAt(j).getUserName() + "\n";
                            }
                        }
                    }
                    string += "were awarded: "+ Integer.toString(chore.getChoreReward());
                } else if(!chore.getStatus() && choreMark == 1){
                    String string = "";
                    listOfUsersAssigned = new String[chore.getAssignedUsers().length];
                    listOfUsersAssigned = chore.getAssignedUsers();
                    for(int i = 0; i < listOfUsersAssigned.length; i++){
                        for(int j = 0; j < userManager.getUserSize(); j++) {
                            if(userManager.getUserAt(j).getUserName().equals(listOfUsersAssigned[i])) {
                                userManager.getUserAt(j).setUserPoints(userManager.getUserAt(j).getUserPoints() - chore.getChoreReward());
                                string += userManager.getUserAt(j).getUserName() + "\n";
                            }
                        }
                    }
                    string += "were awarded: "+ Integer.toString(chore.getChoreReward());
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
