package com.simulatedminds.choreapplication;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by James on 2017-11-30.
 */


public class RewardLists extends Fragment {

    ListView userListView;

    UserManager userData = UserManager.getInstance();
    //User[] userData = AccountCreationActivity.users;
    // array of users
    String[] users = new String[userData.getUserSize()];
    //array of points
    String[] points = new String[userData.getUserSize()];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_profiles, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){


        userListView = (ListView) getView().findViewById(R.id.userListView);
        for (int i=0;i<userData.getUserSize();i++){
            users[i] = userData.getUserAt(i).getUserName();
            points[i] = String.valueOf(userData.getUserAt(i).getUserPoints());
        }

        //ItemAdapter class
        ItemAdapter itemAdapter = new ItemAdapter(getActivity(), users, points);
        userListView.setAdapter(itemAdapter);

        //resetButton
        Button resetBtn = (Button) getView().findViewById(R.id.resetButton);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<userData.getUserSize();i++){
                    userData.getUserAt(i).setUserPoints(0);
                }
            }
        });

       /** //When its clicked
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent showDetailActivity = new Intent(getActivity(), EditUserActivity.class);
        startActivity(showDetailActivity);
        }
        });**/
    }
}