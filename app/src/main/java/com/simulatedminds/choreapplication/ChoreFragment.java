package com.simulatedminds.choreapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by arielkim on 2017-11-22.
 */

public class ChoreFragment extends Fragment {
    private static final String ARG_CHORE_ID = "ARG_CHORE_ID";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private Chore mChore;
    private EditText mTitleField;
    private EditText mDetailsField;
    private Button mDateButton;
    private CheckBox mCompletedCheckBox;

    public static ChoreFragment newInstance(UUID choreId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHORE_ID, choreId);
        ChoreFragment fragment = new ChoreFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    //Keep in mind that unlike an Activity, in a fragment its onCreate is public
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID choreId = (UUID) getArguments().getSerializable(ARG_CHORE_ID);
        mChore = ChoreLab.get(getActivity()).getChore(choreId);
        setHasOptionsMenu(true);
    }

    //Pushes updates
    public void onPause(){
        super.onPause();
        ChoreLab.get(getActivity()).updateChore(mChore);
    }

    //Inflates the fragment's view
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_chore, container, false);
        mTitleField = (EditText) v.findViewById(R.id.chore_title);
        mTitleField.setText(mChore.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                //Left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mChore.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Left blank
            }
        });
        mDetailsField = (EditText) v.findViewById(R.id.chore_detail);
        mDetailsField.setText(mChore.getDetail());
        mDetailsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Left Blank
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mChore.setDetail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Left Blank
            }
        });

        mDateButton = (Button) v.findViewById(R.id.chore_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mChore.getDate());
                dialog.setTargetFragment(ChoreFragment.this, REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
            }
        });

        mCompletedCheckBox = (CheckBox)v.findViewById(R.id.chore_completed);
        mCompletedCheckBox.setChecked(mChore.isCompleted());
        mCompletedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mChore.setCompleted(b);
            }
        });
        return v;
    }

    //Inflates the menu, since it is a fragment, it needs the inflater
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_chore, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.remove_chore:
                ChoreLab.get(getActivity()).deleteChore(mChore);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Retrieves the extra, sets the date on the chore and refreshes the text on the
    //date button
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mChore.setDate(date);
            updateDate();
        }
    }

    //Updates the date in the date button
    private void updateDate() {
        mDateButton.setText(mChore.getDate().toString());
    }


}
