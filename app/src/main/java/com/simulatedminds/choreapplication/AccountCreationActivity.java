package com.simulatedminds.choreapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AccountCreationActivity extends AppCompatActivity {

    public static User[] users;
    public static int sizeOfUsersArray = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);
        setCorrectTextOfButton();
        User[] users = new User[CreateAppActivity.getNumberOfRegularUsers() + CreateAppActivity.getNumberOfChildUsers()]; //makes an array of users
//        populateArrayWithObjects(users);
        ListAdapter accountToCreateAdapter = new AccountCreationAdapter(this, users);
        ListView listView = (ListView) findViewById(R.id.accountsToCreateList);
        listView.setAdapter(accountToCreateAdapter);
        this.users = users;
        this.sizeOfUsersArray = users.length;
    }

    //method to set the text of button id accountsCreationBtn
    private void setCorrectTextOfButton(){
        Button btn = (Button) findViewById(R.id.accountsCreationBtn);
        if(CreateAppActivity.getPasswordSystem()){
            btn.setText("Next");
        } else {
            btn.setText("Finish");
        }
    }

    //sets the usernames into instance objects made
    private void getUserInputAndStoreIt() {
        User[] users = getUsers();
        ListView listView = (ListView) findViewById(R.id.accountsToCreateList);
        View view;
        EditText editText;
        String string;

        for(int i = 0; i < getUsers().length; i++) {
            view = listView.getChildAt(i);
            editText = view.findViewById(R.id.enterNameHere);
            if (editText.getText().toString() != null)
                users[i].setUserName(editText.getText().toString());
            else
                users[i].setUserName("");
        }
    }

    private boolean checkAllUsersForName() {
        User[] users = getUsers();

        for (int i = 0; i < getUsers().length; i++){ //checks if all users have a name
            if(users[i].getUserName().equals("")){
                return false;
            }
            for (int j = i+1; j < getUsers().length; j++){ //checks if all users have unique names
                if (users[i].getUserName().toLowerCase().equals(users[j].getUserName().toLowerCase())){
                    return false;
                }
            }
        }
        return true;
    }
    //method onClick for button accountsCreationBtn
    public void onClickBtnAccountsCreation (View v) {
        Button button = (Button) v;
        //set all stuff written to user objects
        getUserInputAndStoreIt();
        if (checkAllUsersForName()) { //checkAllUsersForName()
            if (button.getText().toString().toLowerCase().equals("next")) {
                if (CreateAppActivity.getPasswordSystem()) {
                    Intent intent = new Intent(this, PasswordCreationActivity.class); //intent is used to launch another activity, make this intent to get passwords from users
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(this, NavigationDrawerActivity.class); //intent is used to launch another activity, make this intent to go to homepage
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Names have to be unique and exist", Toast.LENGTH_LONG).show(); //a way to print in an emulator
        }

    }

//    //populates the array users
//    public void populateArrayWithObjects(User[] users){ //method that populates array with objects user
//        for (int i = 0; i < users.length; i++){
//            users[i] = new User();
//        }
//    }

    //getter method to get users
    public User[] getUsers(){
        return users;
    }
}
