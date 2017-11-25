package com.simulatedminds.choreapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;

/**
 * Created by Hussein on 11/20/2017.
 */

public class AccountCreationAdapter extends ArrayAdapter<User>{

    public AccountCreationAdapter(@NonNull Context context, User[] users) {
        super(context, R.layout.account_layout, users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater accountToCreateInflater = LayoutInflater.from(getContext());
        View customView = accountToCreateInflater.inflate(R.layout.account_layout, parent, false);

        //User singleUser = getItem(position);
        //EditText accountText = (EditText) customView.findViewById(R.id.enterNameHere);

        return customView;

    }
}
