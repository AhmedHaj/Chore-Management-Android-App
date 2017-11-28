package com.simulatedminds.choreapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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

    RecyclerView.ViewHolder holder;
    private User[] users;

    public AccountCreationAdapter(@NonNull Context context, User[] users) {
        super(context, R.layout.account_layout, users);
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater accountToCreateInflater = LayoutInflater.from(getContext());
        View customView = accountToCreateInflater.inflate(R.layout.account_layout, parent, false);

        //User singleUser = getItem(position);
        EditText accountText = (EditText) customView.findViewById(R.id.enterNameHere);

        //two things not working here
        //when u scroll out of listview you lose content
        //when u click finish button where there is an element under it then u crash android
        accountText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                users[position].getUserName();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                users[position].setUserName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                users[position].setUserName(s.toString());
            }
        });


        return customView;

    }
}
