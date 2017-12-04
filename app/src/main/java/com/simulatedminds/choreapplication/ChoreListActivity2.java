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
import android.widget.ListView;

import static android.app.Activity.RESULT_OK;


public class ChoreListActivity2 extends Fragment {

    private ChoreManager manager;
    private ChoreArrayAdapter choreListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        manager = ChoreManager.getInstance();
        return inflater.inflate(R.layout.activity_chore_list2, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        choreListAdapter = new ChoreArrayAdapter(getActivity(), manager.getChoreList());
        ListView listView = (ListView) getView().findViewById(R.id.choreList);
        listView.setAdapter(choreListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {//Enables items in the list to react to clicks
                Intent intent = new Intent(getActivity(), ChoreEditorActivity.class);
                intent.putExtra(ChoreManager.intentIndexTitle, i);
                startActivityForResult(intent, 0);
            }
        });
        Button button = (Button) view.findViewById(R.id.createChoreBtn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), CreateChoreActivity.class);//intent is used to launch another activity, make this intent to go to homepage
                startActivity(intent);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Get ListView object from xml layout
        ListView listView = (ListView) getView().findViewById(R.id.resourceList);

        //TODO: Recycle the layout to display updated information
        if(requestCode == 0){
            if(resultCode == RESULT_OK){

                choreListAdapter.notifyDataSetChanged();
            }
        }

        //Refresh the contents of the screen
//        listView.refreshDrawableState();

    }



}
