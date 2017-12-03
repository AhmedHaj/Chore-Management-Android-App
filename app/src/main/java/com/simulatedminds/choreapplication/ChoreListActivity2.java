package com.simulatedminds.choreapplication;

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


public class ChoreListActivity2 extends Fragment {

    private ChoreManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        manager = ChoreManager.getInstance();
        return inflater.inflate(R.layout.activity_chore_list2, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ListAdapter choreListAdapter = new CreateChoreAdapter(getActivity(), manager.getChoreList());
        ListView listView = (ListView) getView().findViewById(R.id.choreList);
        listView.setAdapter(choreListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {//Enables items in the list to react to clicks
                Chore2 chore = (Chore2)adapterView.getItemAtPosition(i); //To be used to pass data to the intent
                Intent intent = new Intent(getActivity(), ChoreEditorActivity.class);
                startActivity(intent);
            }
        });
        Button button = (Button) view.findViewById(R.id.createChoreBtn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), CreateChoreActivity.class); //intent is used to launch another activity, make this intent to go to homepage
                startActivity(intent);
            }
        });

    }



}
