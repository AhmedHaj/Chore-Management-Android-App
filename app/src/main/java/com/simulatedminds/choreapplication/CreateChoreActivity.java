package com.simulatedminds.choreapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateChoreActivity extends AppCompatActivity {
    private CheckBox statusCheckBox;
    private boolean status;
    Button assignUsers;
    String[] listOfUserNames;
    String[] listOfUserNamesSelected;
    boolean[] checkedItems;
    ArrayList<Integer> usersSelectedItemes = new ArrayList<>();

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
                AlertDialog.Builder assignUsersBuilder = new AlertDialog.Builder(CreateChoreActivity.this);
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
                            for (int j = 0; j < usersSelectedItemes.size(); j++){
                                listOfUserNamesSelected[j] = userManager.getUserAt(usersSelectedItemes.get(j)).getUserName();
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
                    choreDescription.getText().toString(), Integer.parseInt(customReward.getText().toString()), choreResources,status, listOfUserNamesSelected);
            manager.addChore(chore);
            UserManager userManager = UserManager.getInstance();
            if (status){
                String string = "";
                for(int i = 0; i < listOfUserNamesSelected.length; i++){
                    for(int j = 0; j < userManager.getUserSize(); j++) {
                        if(userManager.getUserAt(j).getUserName().equals(listOfUserNamesSelected[i])) {
                            userManager.getUserAt(j).setUserPoints(userManager.getUserAt(j).getUserPoints() + chore.getChoreReward());
                            string += userManager.getUserAt(j).getUserName() + "\n";
                        }
                    }
                }
                string += "were awarded: "+ Integer.toString(chore.getChoreReward());
                Toast.makeText(this, string, Toast.LENGTH_LONG).show(); //a way to print in an emulator
            }

            finish();
        }
    }




}
