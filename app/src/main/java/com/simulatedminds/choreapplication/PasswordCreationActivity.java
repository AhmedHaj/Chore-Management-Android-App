package com.simulatedminds.choreapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PasswordCreationActivity extends AppCompatActivity {

    private User[] users = AccountCreationActivity.users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_creation);
        ListAdapter passwordsToCreateAdapter = new PasswordCreationAdapter(this, this.users);
        ListView listView = (ListView) findViewById(R.id.passwordsToCreateList);
        listView.setAdapter(passwordsToCreateAdapter);
    }

    //sets the passwords into instance objects made
    private void getUserInputAndStoreIt() {
        ListView listView = (ListView) findViewById(R.id.passwordsToCreateList);
        View view;
        EditText editText;
        String string;

        for(int i = 0; i < users.length; i++) {
            view = listView.getChildAt(i);
            editText = view.findViewById(R.id.enterPasswordHere);
            if (editText.getText().toString() != null)
                users[i].setUserPassword(editText.getText().toString());
            else
                users[i].setUserPassword("");
        }
    }

    private boolean checkAllUsersForPassword() {
        for (int i = 0; i < users.length; i++){ //checks if all users have a password
            if(users[i].getUserPassword().equals("")){
                return false;
            }
            for (int j = i+1; j < users.length; j++){ //checks if all users have unique passwords
                if (users[i].getUserPassword().toLowerCase().equals(users[j].getUserPassword().toLowerCase())){
                    return false;
                }
            }
        }
        return true;
    }

    //method onClick for button passwordsCreationBtn
    public void onClickBtnPasswordsCreation (View v) {
        Button button = (Button) v;
        //set all stuff written to user objects
        getUserInputAndStoreIt();
        if (checkAllUsersForPassword()) { //checkAllUsersForName()
            Intent intent = new Intent(this, NavigationDrawerActivity.class); //intent is used to launch another activity, make this intent to get reward system from users
            startActivity(intent);
        } else {
            Toast.makeText(this, "Passwords have to be unique and exist", Toast.LENGTH_LONG).show(); //a way to print in an emulator
        }

    }
}
