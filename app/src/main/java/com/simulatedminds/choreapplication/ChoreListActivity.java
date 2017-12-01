package com.simulatedminds.choreapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

public class ChoreListActivity extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_chore_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // or  (ImageView) view.findViewById(R.id.foo);
        // Get ListView object from xml layout
        ListView listView = (ListView) getView().findViewById(R.id.list);

        //Getting RecipeManager Instance
        ChoreManager manager = ChoreManager.getInstance();

        //Defining Array values to show in ListView
        ChoreArrayAdapter adapter = new ChoreArrayAdapter(getActivity(), manager.getChoreList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent launchTeamEditorIntent = new Intent(getActivity(), ChoreEditorActivity.class);
                launchTeamEditorIntent.putExtra(ChoreManager.intentIndexTitle, position);
                startActivityForResult(launchTeamEditorIntent, 0);
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Get ListView object from xml layout
        ListView listView = (ListView) getView().findViewById(R.id.list);

        //TODO: Recycle the layout to display updated information

        //Refresh the contents of the screen
        listView.refreshDrawableState();
    }
}
