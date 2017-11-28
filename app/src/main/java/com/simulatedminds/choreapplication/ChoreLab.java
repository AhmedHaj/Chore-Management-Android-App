package com.simulatedminds.choreapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.simulatedminds.choreapplication.database.ChoreBaseHelper;
import com.simulatedminds.choreapplication.database.ChoreCursorWrapper;
import com.simulatedminds.choreapplication.database.ChoreDbSchema.ChoreTable;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by arielkim on 2017-11-22.
 */
// This is a temporary singleton solution for demoing the app
public class ChoreLab {
    //s prefix is used for android convention of a static variable
    private static ChoreLab sChoreLab;
    private SQLiteDatabase mDatabase;

    public void addChore(Chore c){

        ContentValues values = getContentValues(c);
        //First argument is the table you want to insert to, second is something called nullColumnHack, third is the values to insert
        mDatabase.insert(ChoreTable.NAME, null, values);
    }

    public void deleteChore(Chore c){
        String uuidString = c.getId().toString();
        mDatabase.delete(ChoreTable.NAME, ChoreTable.Cols.UUID + " = ?", new String[] { uuidString});
    }

    public static ChoreLab get(Context context){
        if (sChoreLab == null){
            sChoreLab = new ChoreLab(context);
        }
        return sChoreLab;
    }

    private ChoreLab (Context context){
        /*getWritableDatabase() opens up the database file location and creates a new one
         *if there already isn't one. The first time it is created, it will call
         * onCreate(SQLiteDatabase) to save the version number. Otherwise, it will check the
         * version and upgrade it to a higher one by calling onUpgrade()
         */
        mDatabase = new ChoreBaseHelper(context.getApplicationContext()).getWritableDatabase();

    }

    public List<Chore> getChores(){
        List<Chore> chores = new ArrayList<>();
        //So the way cursor works is the following:
        //To pull data out of a cursor, you need to move it to the first
        //element using moveToFirst(), then each time you want to move a row
        //you need to call moveToNext(), until finally you reach
        // isAfterLast(). It is important to call close()
        ChoreCursorWrapper cursor = queryChores(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                chores.add(cursor.getChore());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return chores;
    }

    public Chore getChore(UUID id){
        ChoreCursorWrapper cursor = queryChores(ChoreTable.Cols.UUID + " = ?", new String[] { id.toString()});

        try{
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getChore();
        }finally{
            cursor.close();
        }
    }

    //Updates rows in the database
    public void updateChore(Chore chore){
        String uuidString = chore.getId().toString();
        ContentValues values = getContentValues(chore);
        //Third argument here is something called the "where clause"
        //which specifies which row gets updated and then you specify
        //the values for the arguments in this caluse (in this case String array)
        //According to SQL safe practice, the ? prevents sql code to be inputed directly
        //which prevents sql injections since all of it is treated as strings
        mDatabase.update(ChoreTable.NAME, values, ChoreTable.Cols.UUID + " = ?", new String[] { uuidString});
    }

    //Query method used to read data from SQLite
    private ChoreCursorWrapper queryChores(String whereClause,  String[] whereArgs){
        Cursor cursor = mDatabase.query(ChoreTable.NAME, null, whereClause, whereArgs, null, null, null);

        return new ChoreCursorWrapper(cursor);
    }
    //Assistance class that stores key values which writes and updates the database
    private static ContentValues getContentValues(Chore chore){
        ContentValues values = new ContentValues();
        values.put(ChoreTable.Cols.UUID, chore.getId().toString());
        values.put(ChoreTable.Cols.TITLE, chore.getTitle());
        values.put(ChoreTable.Cols.DETAILS, chore.getDetail());
        values.put(ChoreTable.Cols.DATE, chore.getDate().getTime());
        values.put(ChoreTable.Cols.COMPLETED, chore.isCompleted() ? 1 : 0);
        return values;
    }
}
