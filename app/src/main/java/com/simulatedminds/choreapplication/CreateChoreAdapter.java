package com.simulatedminds.choreapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Hussein on 2017-11-30.
 */

public class CreateChoreAdapter extends ArrayAdapter<Chore2> {

    private Chore2[] chores;

    public CreateChoreAdapter(@NonNull Context context, Chore2[] chores) {
        super(context, R.layout.chore_layout, chores);
        this.chores = chores;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater accountToCreateInflater = LayoutInflater.from(getContext());
        View customView = accountToCreateInflater.inflate(R.layout.chore_layout, parent, false);
        String reallyLongString = "";
        TextView choreTitle = (TextView) customView.findViewById(R.id.choreTitle);
        TextView choreDetails = (TextView) customView.findViewById(R.id.choreDetails);

        choreTitle.setText("Chore Title: " + chores[position].getChoreTitle());
        //
        if (!chores[position].getChoreDescription().equals(""))
            reallyLongString += "Chore Description: " + chores[position].getChoreDescription() + "\n";
        reallyLongString += "Reward: " + Integer.toString(chores[position].getChoreReward()) + "\n";

        for(int j = 0; j < chores[position].getChoreResources().length; j++){  //checking if there is resources
            if(!chores[position].getChoreResources()[j].equals(""))
                reallyLongString += "Chore Resource " + (j+1) + ") " + chores[position].getChoreResources()[j] + "\n";
        }
        choreDetails.setText(reallyLongString);
        return customView;
    }





}
