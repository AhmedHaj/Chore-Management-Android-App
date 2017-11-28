package com.simulatedminds.choreapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by arielkim on 2017-11-23.
 */

public class ChorePagerActivity extends AppCompatActivity {
    private static final String EXTRA_CHORE_ID = "com.uottawa.android.choreapplication.chore_id";
    private ViewPager mViewPager;
    private List<Chore> mChores;

    public static Intent newIntent(Context packageContext, UUID choreId){
        Intent intent = new Intent(packageContext, ChorePagerActivity.class);
        intent.putExtra(EXTRA_CHORE_ID, choreId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_pager);
        UUID choreId = (UUID) getIntent().getSerializableExtra(EXTRA_CHORE_ID);
        mViewPager = (ViewPager) findViewById(R.id.chore_view_pager);
        //Gets data set from the list of chores
        mChores = ChoreLab.get(this).getChores();
        //Gets the activity's instance of Fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //The adapter is an unnamed instance of FragmentStatePagerAdapter,
        //Which is the one that manages the "communication" with the ViewPager
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            //Fetches the Chore instance for a given position in the data set, then
            //uses it's unique id to create and return a properly configured chore
            //fragment
            public Fragment getItem(int position) {
                Chore chore = mChores.get(position);
                return ChoreFragment.newInstance(chore.getId());
            }

            @Override
            //Returns number of items in array list
            public int getCount() {
                return mChores.size();
            }
        });
        //The pager by default shows the first item in the list. This
        //allows it to show the selected item.

        for(int i = 0; i < mChores.size(); i++){
            if(mChores.get(i).getId().equals(choreId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
