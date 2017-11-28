package com.simulatedminds.choreapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.simulatedminds.choreapplication.database.ChoreDbSchema.ChoreTable;

/**
 * Created by arielkim on 2017-11-26.
 * Makes use of the SQLiteOpenHelper provided by android
 * to do the following:
 * Check if there is an existing database
 * Create it along it's tables and data it needs
 * Open the database if there is an existing one and
 * check the version it has. If it is an older one,
 * upgrade it to the current version
 */

public class ChoreBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "choreBase.db";

    public ChoreBaseHelper(Context context){
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + ChoreTable.NAME + "(" + " _id integer primary key autoincrement, " + ChoreTable.Cols.UUID + ", " + ChoreTable.Cols.TITLE + ", " + ChoreTable.Cols.DETAILS + ", " + ChoreTable.Cols.DATE + ", " + ChoreTable.Cols.COMPLETED + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
