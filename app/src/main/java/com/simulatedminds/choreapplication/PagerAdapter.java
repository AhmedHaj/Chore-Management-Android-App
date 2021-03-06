package com.simulatedminds.choreapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Ahmed on 11/24/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NoOfTabs) {

        super(fm);
        this.mNoOfTabs = NoOfTabs;

    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {

            case 0:
                TabResources resourceList = new TabResources();
                return resourceList;
            case 1:
                TabChores choreList = new TabChores();
                return choreList;
            case 2:
                TabProfiles profileList = new TabProfiles();
                return profileList;


        }
        return null;
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

}
