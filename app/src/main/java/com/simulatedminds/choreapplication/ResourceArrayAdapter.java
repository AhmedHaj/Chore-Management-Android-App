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

//This class allows us to display Resource objects as a list.

public class ResourceArrayAdapter extends ArrayAdapter<Resource>  {
    /**/
    private final Context context;
    private final ArrayList<Resource> resources;

    public ResourceArrayAdapter(Context context, ArrayList<Resource> values) {
        super(context, R.layout.resource_item_layout, values);
        this.context = context;
        this.resources = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Getting Resource
        Resource curResource = resources.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.resource_item_layout, parent, false);
        TextView resourceName = (TextView) rowView.findViewById(R.id.itemName);
        TextView resourceDescription = (TextView) rowView.findViewById(R.id.itemDescription);
        ImageView resourceImage = (ImageView) rowView.findViewById(R.id.icon);

        //Placing content into Resource List Item
        resourceName.setText(curResource.getResourceName());
        resourceDescription.setText((curResource.getResourceDescription()));

        //TODO: Perform decision regarding image selection for resource prior to setting an image
        resourceImage.setImageResource(R.drawable.questionmark);

        return rowView;
    }
}