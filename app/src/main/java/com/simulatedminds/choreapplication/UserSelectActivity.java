package com.simulatedminds.choreapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * Created by Jonathan Calles on 12/3/2017.
 */

public class UserSelectActivity extends AppCompatActivity {

    private UserManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);


        // Get ListView object from xml layout
        ListView listView = (ListView) findViewById(R.id.userList);

        //Getting UserManager Instance
        manager = UserManager.getInstance();

        //Defining Array values to show in ListView
        UserArrayAdapter adapter = new UserArrayAdapter(this, manager.getUserList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                //TODO: Create User editor screen
                //Intent intent = new Intent(getApplicationContext(), UserEditorActivity.class);
                //intent.putExtra(UserManager.intentIndexTitle,position);
                //startActivityForResult(intent, 0);
            }
        });

    }





    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Get ListView object from xml layout
        ListView listView = (ListView) findViewById(R.id.list);

        //TODO: Recycle the layout to display updated information

        //Refresh the contents of the screen
        listView.refreshDrawableState();
    }
}


