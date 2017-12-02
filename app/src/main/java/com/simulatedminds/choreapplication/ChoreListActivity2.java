package com.simulatedminds.choreapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChoreListActivity2 extends Fragment {

    public static int sizeOfChoreList = 0;
    public static Chore2[] choreList = new Chore2[sizeOfChoreList];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_chore_list2, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ListAdapter choreListAdapter = new CreateChoreAdapter(getActivity(), this.choreList);
        ListView listView = (ListView) getView().findViewById(R.id.choreList);
        listView.setAdapter(choreListAdapter);
        Toast.makeText(getActivity(), Integer.toString(sizeOfChoreList), Toast.LENGTH_LONG).show();
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
