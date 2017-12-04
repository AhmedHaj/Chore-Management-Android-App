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

//This class allows us to display User objects as a list.

public class UserArrayAdapter extends ArrayAdapter<User>  {
    /**/
    private final Context context;
    private final ArrayList<User> users;

    public UserArrayAdapter(Context context, ArrayList<User> values) {
        super(context, R.layout.user_item_layout, values);
        this.context = context;
        this.users = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Getting User
        User curUser = users.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.user_item_layout, parent, false);
        TextView userName = (TextView) rowView.findViewById(R.id.itemName);
        TextView userPassword = (TextView) rowView.findViewById(R.id.itemPassword);
        ImageView userImage = (ImageView) rowView.findViewById(R.id.icon);

        //Placing content into User List Item
        userName.setText(curUser.getUserName());
        userPassword.setText((curUser.getUserPassword()));

        //TODO: Perform decision regarding image selection for chore prior to setting an image
        userImage.setImageResource(R.drawable.questionmark);

        return rowView;
    }
}