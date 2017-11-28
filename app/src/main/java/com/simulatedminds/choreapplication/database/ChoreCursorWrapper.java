package com.simulatedminds.choreapplication.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.simulatedminds.choreapplication.Chore;

import java.util.Date;
import java.util.UUID;

import static com.simulatedminds.choreapplication.database.ChoreDbSchema.*;


/**
 * Created by arielkim on 2017-11-27.
 */

//This class basically prevents writing the code to read the information from the cursor
//Over and over again, which is a subclass of CursorWrapper which lets you place new methods on top of a Cursor
public class ChoreCursorWrapper extends CursorWrapper {
    public ChoreCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Chore getChore(){
        String uuidString = getString(getColumnIndex(ChoreTable.Cols.UUID));
        String title = getString(getColumnIndex(ChoreTable.Cols.TITLE));
        String details = getString(getColumnIndex(ChoreTable.Cols.DETAILS));
        long date = getLong(getColumnIndex(ChoreTable.Cols.DATE));
        int isCompleted = getInt(getColumnIndex(ChoreTable.Cols.COMPLETED));

        Chore chore = new Chore(UUID.fromString(uuidString));
        chore.setTitle(title);
        chore.setDetail(details);
        chore.setDate(new Date(date));
        chore.setCompleted(isCompleted != 0);

        return chore;
    }
}
