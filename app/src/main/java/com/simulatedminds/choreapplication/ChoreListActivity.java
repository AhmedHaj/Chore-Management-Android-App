package com.simulatedminds.choreapplication;

import android.support.v4.app.Fragment;

/**
 * Created by arielkim on 2017-11-22.
 */
//Currently this is declared in the manifest as the starting
//activity be sure to change that once it is linked to the other parts!

/**
 *         <activity android:name=".ChoreListActivity">
 *<intent-filter>
 *<action android:name="android.intent.action.MAIN" />
 *<category android:name="android.intent.category.LAUNCHER" />
 *</intent-filter>
 *</activity>
 */
public class ChoreListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ChoreListFragment();
    }
}
