package com.simulatedminds.choreapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//TODO: Replace String type chore to actual chores once we get a chore database


public class ResourceDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "resourceDB.db";
    public static final String TABLE_RESOURCES = "resources";
    public static final String COLUMN_RESOURCENAME = "resourcename";
    public static final String COLUMN_CHORES = "chores";
    private static ResourceDB instance = null;

    public ResourceDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized ResourceDB getInstance(Context context){

        if (instance == null){
            instance = new ResourceDB(context);

        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RESOURCES_TABLE = "CREATE TABLE " +
                TABLE_RESOURCES + "("
                + COLUMN_RESOURCENAME + " TEXT," + COLUMN_CHORES + " TEXT" + ")";
        db.execSQL(CREATE_RESOURCES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESOURCES);
        onCreate(db);
    }

    public void addResource(Resource resource){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_RESOURCENAME, resource.getResourceName());
        values.put(COLUMN_CHORES, resource.getChore());

        db.insert(TABLE_RESOURCES, null, values);

        db.close();
    }

    public Resource findResource(String resourceName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_RESOURCES + " WHERE " + COLUMN_RESOURCENAME + " = \"" + resourceName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Resource resource = new Resource();

        if(cursor.moveToFirst()){
            resource.setResourceName(cursor.getString(0));
            resource.setChore(cursor.getString(1));
            cursor.close();
        } else {
            resource = null;
        }
        db.close();
        return resource;
    }

    public boolean deleteResource(String resourcename){

        boolean result = false;

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_RESOURCES + " WHERE " + COLUMN_RESOURCENAME + " = \"" + resourcename + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_RESOURCES, COLUMN_RESOURCENAME + "=" +  idStr, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;

    }

    public ArrayList<Resource> retrieveAll(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_RESOURCES;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList list = new ArrayList<Resource>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Resource resource = new Resource(cursor.getString(0),cursor.getString(1));
                resource.setResourceName(cursor.getString(0));
                resource.setChore(cursor.getString(1));

                list.add(resource);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();
        return list;
    }



}
