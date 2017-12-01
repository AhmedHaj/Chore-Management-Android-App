package com.simulatedminds.choreapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Jonathan Calles on 11/29/2017.
 * Adapted from the Fall 2017 SEG2105 Lab7 Solutions
 */


/**ArrayAdapter is an Android SDK class for adapting an array of objects as a datasource.
 * Adapters are used by Android to treat a result set uniformly whether it's from a database,
 * file, or in-memory objects so that it can be displayed in a UI element.
 */

//This class allows us to display Chore objects as a list.

public class ChoreArrayAdapter extends ArrayAdapter<Chore>  {
    /**/
    private final Context context;
    private final ArrayList<Chore> chores;

    public ChoreArrayAdapter(Context context, ArrayList<Chore> values) {
        super(context, R.layout.chore_item_layout, values);
        this.context = context;
        this.chores = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Getting Chore
        Chore curChore = chores.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.chore_item_layout, parent, false);
        TextView choreName = (TextView) rowView.findViewById(R.id.itemName);
        TextView choreDescription = (TextView) rowView.findViewById(R.id.itemDescription);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);

        //Placing content into Chore List Item
        choreName.setText(curChore.getChoreName());
        choreDescription.setText((curChore.getChoreDescription()));

        //TODO: Perform decision regarding image selection for chore prior to setting an image
        choreImage.setImageResource(R.drawable.questionmark);

        return rowView;
    }
}