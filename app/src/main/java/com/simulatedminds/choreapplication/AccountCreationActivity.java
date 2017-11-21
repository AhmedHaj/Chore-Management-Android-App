package com.simulatedminds.choreapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AccountCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);
        User[] users = new User[CreateAppActivity.getNumberOfRegularUsers() + CreateAppActivity.getNumberOfChildUsers()]; //makes an array of users
        ListAdapter accountToCreateAdapter = new AccountCreationAdapter(this, users);
        ListView listView = (ListView) findViewById(R.id.accountsToCreateList);
        listView.setAdapter(accountToCreateAdapter);
    }
}
