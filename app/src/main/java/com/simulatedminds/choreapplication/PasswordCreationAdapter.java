package com.simulatedminds.choreapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Hussein on 2017-12-01.
 */

public class PasswordCreationAdapter extends ArrayAdapter<User> {
    RecyclerView.ViewHolder holder;
    private User[] users;

    public PasswordCreationAdapter(@NonNull Context context, User[] users) {
        super(context, R.layout.account_password_layout, users);
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater passwordToCreateInflater = LayoutInflater.from(getContext());
        View customView = passwordToCreateInflater.inflate(R.layout.account_password_layout, parent, false);

        //User singleUser = getItem(position);
        EditText passwordText = (EditText) customView.findViewById(R.id.enterPasswordHere);
        TextView accountNameText = (TextView) customView.findViewById(R.id.accountNameView);
        accountNameText.setText("Account " + Integer.toString(position) + ": " +
                AccountCreationActivity.users[position].getUserName());


        //two things not working here
        //when u scroll out of listview you lose content
        //when u click finish button where there is an element under it then u crash android
        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                users[position].getUserPassword();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                users[position].setUserPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                users[position].setUserName(s.toString());
            }
        });


        return customView;

    }
}
