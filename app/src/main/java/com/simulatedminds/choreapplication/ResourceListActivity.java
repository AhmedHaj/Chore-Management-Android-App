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

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */

public class ResourceListActivity extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_resource_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // or  (ImageView) view.findViewById(R.id.foo);
        // Get ListView object from xml layout
        ListView listView = (ListView) getView().findViewById(R.id.resourceList);

        //Getting RecipeManager Instance
        ResourceManager manager = ResourceManager.getInstance();
        ResourceDB db = new ResourceDB(getActivity());

//        String[] values = new String[]{
//                "Soap", "Water", "Vacuum", "Broom", "Sponge", "Bucket"
//        };
//
//        String[] descriptions = new String[]{
//                "Soapy", "Wet", "Suck", "Sweep", "Squeeze", "What?"
//        };
//
//        for (int i = 0; i < values.length ; i++) {
//            Resource newResource = new Resource(values[i],descriptions[i]);
//            db.addResource(newResource);
//        }

        //Defining Array values to show in ListView
        ResourceArrayAdapter adapter = new ResourceArrayAdapter(getActivity(), db.retrieveAll());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent launchTeamEditorIntent = new Intent(getActivity(), ResourceEditorActivity.class);
                launchTeamEditorIntent.putExtra(ResourceManager.intentIndexTitle, position);
                startActivityForResult(launchTeamEditorIntent, 0);
            }
        });


        //Button listener and function for when the button ("Create Resource!") is clicked
        Button button = (Button) view.findViewById(R.id.createResourceBtn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ResourceEditorActivity.class);//intent is used to launch another activity, make this intent to go to homepage
                startActivity(intent);
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
