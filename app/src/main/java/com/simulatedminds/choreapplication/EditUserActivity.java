package com.simulatedminds.choreapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
    }
    private EditText name;
    public void buttonOnClick(View v) {
        Button button = (Button) v;
        name = (EditText) findViewById(R.id.editname);
    }
}
