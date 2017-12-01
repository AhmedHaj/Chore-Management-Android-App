package com.simulatedminds.choreapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by James on 2017-11-30.
 */

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] users;
    int[] points;

    //constructor
    public ItemAdapter(Context c, String[] u, int[] p){
        users=u;
        points=p;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.length;
    }

    @Override
    public Object getItem(int i) {
        return users[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = mInflater.inflate(R.layout.rewardsystem_layout, null);


        TextView userTextView = (TextView) v.findViewById(R.id.username); //display user name
        TextView pointTextView = (TextView) v.findViewById(R.id.userpoints); //display user points

        String name = users[i];
        int point = points[i];

        //setting name,points for textview
        userTextView.setText(name);
        pointTextView.setText(point);

        return v;
    }
}
